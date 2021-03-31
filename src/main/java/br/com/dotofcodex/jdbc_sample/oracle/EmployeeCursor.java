package br.com.dotofcodex.jdbc_sample.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.dotofcodex.jdbc_sample.oracle.datasource.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class EmployeeCursor {
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;

		// Read User Inputs
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Employee Role:");
		String role = input.nextLine();

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareCall("{call get_employee_by_role(?,?)}");
			stmt.setString(1, role);

			// register the OUT parameter before calling the stored procedure
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();

			// read the OUT parameter now
			rs = (ResultSet) stmt.getObject(2);
			while (rs.next()) {
				System.out.println("Employee ID=" + rs.getInt("empId") + ",Name=" + rs.getString("name") + ",Role="
						+ role + ",City=" + rs.getString("city") + ",Country=" + rs.getString("country"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
				input.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
