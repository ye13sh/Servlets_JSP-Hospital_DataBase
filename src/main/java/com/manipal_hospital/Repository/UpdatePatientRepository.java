package com.manipal_hospital.Repository;

import com.manipal_hospital.Constants.QueryConstant;
import com.manipal_hospital.DTO.PatientDTO;
import com.manipal_hospital.Exception.DB_ERROR_Exception;
import com.manipal_hospital.Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdatePatientRepository {
    private static Connection connection;
    private static ConnectionUtil util;
    private static PreparedStatement pstmt;

    public UpdatePatientRepository() {
        util=new ConnectionUtil();
    }

    public void updatePatient(PatientDTO patientDTO)throws DB_ERROR_Exception{
        try {
            connection= util.getConnection();
            pstmt=connection.prepareStatement(QueryConstant.Update_Patient_query);
            pstmt.setInt(1,patientDTO.getId());
            pstmt.setString(2, patientDTO.getName());
            pstmt.setInt(3, patientDTO.getAge());
            pstmt.setString(4, patientDTO.getDoctor_name());
            pstmt.setString(5, patientDTO.getTreatment());
            pstmt.setInt(6, patientDTO.getContact());
            pstmt.setString(7, patientDTO.getAddress());

            pstmt.executeUpdate();
        }catch (Exception e){
            throw new DB_ERROR_Exception("Error while connecting updatePatient repository",e);
        }finally {
           try {
               connection.close();
               pstmt.close();
           }catch (Exception e){
               throw new DB_ERROR_Exception("Error while closing connection & pstmt",e);
           }
        }
    }
}
