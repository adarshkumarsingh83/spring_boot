package com.espark.adarsh.bean;

import lombok.Data;

@Data
public class ResponseBean {

      EmployeeBean employeeBean;

      DeptBean deptBean;


      AddressBean addressBean;

      public EmployeeBean getEmployeeBean() {
            return employeeBean;
      }

      public void setEmployeeBean(EmployeeBean employeeBean) {
            this.employeeBean = employeeBean;
      }

      public DeptBean getDeptBean() {
            return deptBean;
      }

      public void setDeptBean(DeptBean deptBean) {
            this.deptBean = deptBean;
      }

      public AddressBean getAddressBean() {
            return addressBean;
      }

      public void setAddressBean(AddressBean addressBean) {
            this.addressBean = addressBean;
      }
}
