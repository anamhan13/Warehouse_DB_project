package data.access.object;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;
import model.*;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
@SuppressWarnings("unused")
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*
	 * Method using reflection technique for retrieving the values of the fields of an object of class T, which are kept in an ArrayList
	 */
	protected List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					field.setAccessible(true);
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
			return list;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/*
	 * SELECT * FROM table_name WHERE field_name=?
	 *	
	 * @param: filed - string that specifies the name of the column on which the search relies
	 * @return: a string to be used as a query by the data base
	 */
	public String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName().toLowerCase());
		if (field != null) {
			sb.append(" WHERE " + field + " = ?");
		}
		return sb.toString();
	}

	public List<T> findAll() throws IllegalArgumentException, IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(null);
		List<T> objects = new ArrayList<T>();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			objects = createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		if (objects == null) 
			return null;
		return objects;
	}

	public T findByAs(int id, String idS) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<T> list = null;
		String query = createSelectQuery(idS);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			list = createObjects(resultSet);
			PropertyDescriptor pd = new PropertyDescriptor(type.getDeclaredFields()[0].getName(), type);
			Method method = pd.getWriteMethod();
			if (list.size()!=0) {
				method.invoke(list.get(0), id);
				System.out.println("[FIND BY] The requested entry was found!");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findByAs " + e.getMessage());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		if (list.size()==0) {
			System.out.println("[FIND BY] Couldn't find the requested entry");
			return null;
		}
		return list.get(0);
	}
	
	/*
	 * INSERT INTO table_name (field1,field2,...,field_n) VALUES (?,?,...,?) 
	 *	
	 * @param: t - object to be inserted, for which we take the name of the fields and use them to fill in the row in that table
	 * @return: a string to be used as a query by the data base
	 */
	private String createInsertQuery(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + type.getSimpleName().toLowerCase() +" (");
		Field[] fields = t.getClass().getDeclaredFields(); 
		for (Field field : fields) {
			if (!field.getName().equals("id" + type.getSimpleName())){
				sb.append(field.getName());
				sb.append(",");
			}
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(") VALUES (");
		for (Field field : fields) {
			if (!field.getName().equals("id" + type.getSimpleName())){
				sb.append("?,");
			}
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(")");
		return sb.toString();
	}

	public int insert(T t) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		ResultSet rs =null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(createInsertQuery(t), Statement.RETURN_GENERATED_KEYS);
			int i=0;
			Field[] fields = t.getClass().getDeclaredFields(); 
			for (Field field : fields) {
				if (!field.getName().equals("id" + type.getSimpleName())){
					i++;
					String typeF = field.getType().getSimpleName();
					field.setAccessible(true);
					if (typeF.equals("String")) {
					 	insertStatement.setString(i, (String) field.get(t));
					} else if (typeF.equals("int")) {
						insertStatement.setInt(i, field.getInt(t));
					} else if (typeF.equals("double")) {
						insertStatement.setDouble(i, field.getDouble(t));
					}
				}
			}
			insertStatement.executeUpdate();
			rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
				PropertyDescriptor pd = new PropertyDescriptor(type.getDeclaredFields()[0].getName(), type);
				Method method = pd.getWriteMethod();
				method.invoke(t, insertedId);
				System.out.println("[INSERT] Entry in table "+type.getSimpleName().toLowerCase()+" successfully inserted!");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getSimpleName() + "DAO:insert " + e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
			ConnectionFactory.close(rs);
		}
		if (insertedId==-1) {
			System.out.println("[INSERT] Couldn't insert data in table " + type.getSimpleName().toLowerCase());
		}
		return insertedId;
	}
	
	/*
	 * UPDATE table_name SET field1=?,field2=?,...,field_n=? WHERE id=? 
	 * @param: idS - string that specifies the name of the column on which the search relies
	 * @param: id - the id to be searched
	 * @param: fileds -  the fields to be updated
	 * @return: a string to be used as a query by the data base
	 */
	private String createUpdateQuery(String idS, int id,Field... fields) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName().toLowerCase());
		sb.append(" SET ");
		for (Field field : fields) {
			if (!field.getName().toLowerCase().equals(idS)){
				sb.append(field.getName());
				sb.append("=?, ");
			}
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("WHERE "+idS+"=?");
		return sb.toString();
	}

	public int update(String idS, int id, Object o) {
		Connection connection = null;
		PreparedStatement updateStatement = null;
		ResultSet resultSet = null;
		int updatedId=-1;
		String query = createUpdateQuery(idS,id,o.getClass().getDeclaredFields());
		try {
			connection = ConnectionFactory.getConnection();
			updateStatement = connection.prepareStatement(query);
			T t = this.findByAs(id, idS);
			updatedId=id;
			if (t==null) {
				updatedId=-1;
				System.out.println("[UPDATE] There is no such entry in this table");
			}
			int i = 0;
			for (Field field : o.getClass().getDeclaredFields()) {
				if (!field.getName().toLowerCase().equals(idS)){ //checking if name of field is the same with id
					i++;
					String typeF = field.getType().getSimpleName();
					field.setAccessible(true);
					if (typeF.equals("String")) {
						updateStatement.setString(i, (String) field.get(o));
					} else if (typeF.equals("int")) {
						updateStatement.setInt(i, field.getInt(o));
					} else if (typeF.equals("double")) {
						updateStatement.setDouble(i, field.getDouble(o));
					}
				}
			}
			i++;
			updateStatement.setInt(i, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(connection);
		}
		if (updatedId>0) {
			System.out.println("[UPDATE] Entry in table "+type.getSimpleName().toLowerCase()+" successfully updated!");
		}
		return updatedId;
	}
	
	/*
	 * DELETE FROM table_name WHERE id=?
	 *
	 * @param: idS - string that specifies the name of the column on which the search relies
	 * @param: id - the id to be searched
	 * @return: a string to be used as a query by the data base
	 */
	private String createDeleteQuery(String idS, int id) { 
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName().toLowerCase());
		sb.append(" WHERE "+idS+"=?");
		return sb.toString();
	}
	
	public int delete(String idS, int id) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		String query = createDeleteQuery(idS,id);
		int deletedId = -1;
		try {
			T t = this.findByAs(id, idS);
			if (t==null) {
				System.out.println("[DELETE] There is no such entry in this table");
				return -1;
			}
			deleteStatement = connection.prepareStatement(query);	
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getSimpleName()+"DAO:delete " + e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(connection);
		}
		System.out.println("[DELETE] Entry in table "+type.getSimpleName().toLowerCase()+" successfully deleted!");
		return deletedId;
	}
}