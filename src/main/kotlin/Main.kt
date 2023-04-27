import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

fun main(args: Array<String>) {

    val italianStudents = ArrayList<Students>()
    val germanStudents = ArrayList<Students>()

    try {
        val connection: Connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/pinodb", "developer", "developer")
        val statement: Statement = connection.createStatement()

        statement.executeUpdate("CREATE VIEW italian_students AS SELECT first_name, last_name FROM students WHERE country = 'Italy'")
        statement.executeUpdate("CREATE VIEW german_students AS SELECT first_name, last_name FROM students WHERE country = 'Germany'")

        var resultSet = statement.executeQuery("select first_name,last_name from italian_students")

        while (resultSet.next()) {
            val name = resultSet.getString("first_name")
            val surname = resultSet.getString("last_name")
            italianStudents.add(Students(name, surname))
        }
        resultSet = statement.executeQuery("Select first_name, last_name from german_students")
        while (resultSet.next()){
            val name = resultSet.getString("first_name")
            val surname = resultSet.getString("last_name")
            germanStudents.add(Students(name, surname))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}