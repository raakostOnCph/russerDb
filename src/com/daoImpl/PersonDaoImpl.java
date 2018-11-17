package com.daoImpl;

import Util.ConnectionConfiguration;
import com.dao.PersonDao;
import com.entities.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    @Override
    public void createPersonTable() {

        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT  EXISTS person(id int primary key unique auto_increment, " +
                    "first_name varchar (55), last_name varchar (55))");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if(statement != null ){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void insert(Person person) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO PERSON (FIRST_NAME, LAST_NAME)" +
                    "VALUES (?,?)");

            preparedStatement.setString(1,person.getFirstName());
            preparedStatement.setString(2,person.getLastName());
            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO PERSON (FIRST_NAME, LAST_NAME)" );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {

                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Person selectById(int id) {

        Person person = new Person();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();

            preparedStatement = connection.prepareStatement("select * from person where  id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                person.setId(resultSet.getInt("id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement!= null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return person;
    }

    @Override
    public List<Person> selectAll() {

        List<Person> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM  person");

            while (resultSet.next()) {

                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));

                list.add(person);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {

                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }


        return list;
    }

    @Override
    public void delete(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("delete from person where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("delete from person where id=?");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        if (preparedStatement!= null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!= null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        }


    }

    @Override
    public void update(Person person, int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("update person set " +
                    "first_name = ?, last_name = ? where id = ?");

            preparedStatement.setString(1,person.getFirstName());
            preparedStatement.setString(2,person.getLastName());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
            System.out.println("updatere person med id " + id);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement!= null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }





    }
}
