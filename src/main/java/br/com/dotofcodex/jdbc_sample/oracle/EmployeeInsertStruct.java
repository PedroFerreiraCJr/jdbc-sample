package br.com.dotofcodex.jdbc_sample.oracle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.dotofcodex.jdbc_sample.oracle.datasource.ConnectionFactory;
import oracle.jdbc.OracleCallableStatement;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class EmployeeInsertStruct {
	public static void main(String[] args) {
		Connection conn = null;
		OracleCallableStatement stmt = null;

		// Create Object Array for Stored Procedure call
		Object[] empObjArray = new Object[5];

		// Read User Inputs
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Employee ID (int):");
		empObjArray[0] = Integer.parseInt(input.nextLine());
		System.out.println("Enter Employee Name:");
		empObjArray[1] = input.nextLine();
		System.out.println("Enter Employee Role:");
		empObjArray[2] = input.nextLine();
		System.out.println("Enter Employee City:");
		empObjArray[3] = input.nextLine();
		System.out.println("Enter Employee Country:");
		empObjArray[4] = input.nextLine();

		try {
			conn = ConnectionFactory.getConnection();

			StructDescriptor empStructDesc = StructDescriptor.createDescriptor("employee_obj", conn);
			STRUCT empStruct = new STRUCT(empStructDesc, conn, empObjArray);
			stmt = (OracleCallableStatement) conn.prepareCall("{call insert_employee_obj(?,?)}");
			stmt.setSTRUCT(1, empStruct);

			// register the OUT parameter before calling the stored procedure
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			stmt.executeUpdate();

			// read the OUT parameter now
			String result = stmt.getString(2);
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
