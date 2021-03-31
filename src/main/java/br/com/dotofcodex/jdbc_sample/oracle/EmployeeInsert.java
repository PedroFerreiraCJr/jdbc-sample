package br.com.dotofcodex.jdbc_sample.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.dotofcodex.jdbc_sample.oracle.datasource.ConnectionFactory;

public class EmployeeInsert {
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement stmt = null;

		// Read User Inputs
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Employee ID (int):");
		int id = Integer.parseInt(input.nextLine());
		System.out.println("Enter Employee Name:");
		String name = input.nextLine();
		System.out.println("Enter Employee Role:");
		String role = input.nextLine();
		System.out.println("Enter Employee City:");
		String city = input.nextLine();
		System.out.println("Enter Employee Country:");
		String country = input.nextLine();

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareCall("{call insert_employee(?,?,?,?,?,?)}");
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, role);
			stmt.setString(4, city);
			stmt.setString(5, country);

			// register the OUT parameter before calling the stored procedure
			stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			stmt.executeUpdate();

			// read the OUT parameter now
			String result = stmt.getString(6);
			System.out.println("Employee Record Save Success::" + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				input.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
