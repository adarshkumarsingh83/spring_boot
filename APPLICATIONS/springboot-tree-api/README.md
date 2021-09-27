# Springboot Tree Api

---

### To Build
* mvn clean package

### To Run 
* mvn spring-boot:run 


### log into the homepage of db
* http://localhost:8080/h2-console
* username, pwd , dburl and db driver class is mentioned in application.properties file

## Api Url 

### To fetch all the department
* http://localhost:8080/departments
```` 
[
  {
    "deptId": 101,
    "deptName": "management",
    "parentDeptId": 0
  },
  {
    "deptId": 1111,
    "deptName": "It",
    "parentDeptId": 101
  },
  {
    "deptId": 2222,
    "deptName": "Finance",
    "parentDeptId": 101
  },
  {
    "deptId": 3333,
    "deptName": "HR",
    "parentDeptId": 101
  },
  {
    "deptId": 4444,
    "deptName": "OP",
    "parentDeptId": 101
  }
]
````

### To fetch dept based on parent dept
* http://localhost:8080/departments/child/0
```` 
[
  {
    "deptId": 101,
    "deptName": "management",
    "parentDeptId": 0
  }
]
````
* http://localhost:8080/departments/child/101
```` 
[
  {
    "deptId": 1111,
    "deptName": "It",
    "parentDeptId": 101
  },
  {
    "deptId": 2222,
    "deptName": "Finance",
    "parentDeptId": 101
  },
  {
    "deptId": 3333,
    "deptName": "HR",
    "parentDeptId": 101
  },
  {
    "deptId": 4444,
    "deptName": "OP",
    "parentDeptId": 101
  }
]
````

### To fetch all the employees
* http://localhost:8080/employees
```` 
[
  {
    "id": 1,
    "name": "radha",
    "designation": "founder-mgr",
    "managerId": 0,
    "deptId": 101
  },
  {
    "id": 100,
    "name": "adarsh",
    "designation": "it-mgr",
    "managerId": 1,
    "deptId": 1111
  },
  {
    "id": 110,
    "name": "ajit",
    "designation": "it-lead",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 120,
    "name": "mulla",
    "designation": "it-admin",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 121,
    "name": "aaa",
    "designation": "developer",
    "managerId": 110,
    "deptId": 1111
  },
  {
    "id": 130,
    "name": "vishal",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 131,
    "name": "bbb",
    "designation": "sr-developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 133,
    "name": "ccc",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 135,
    "name": "ddd",
    "designation": "jr-developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 138,
    "name": "eee",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 200,
    "name": "amit",
    "designation": "finance-mgr",
    "managerId": 1,
    "deptId": 2222
  },
  {
    "id": 220,
    "name": "111",
    "designation": "ca",
    "managerId": 200,
    "deptId": 3333
  },
  {
    "id": 230,
    "name": "222",
    "designation": "ca",
    "managerId": 200,
    "deptId": 3333
  },
  {
    "id": 400,
    "name": "op-1",
    "designation": "op-mgr",
    "managerId": 1,
    "deptId": 4444
  },
  {
    "id": 500,
    "name": "hr-1",
    "designation": "hr-mgr",
    "managerId": 1,
    "deptId": 3333
  },
  {
    "id": 1001,
    "name": "op-2",
    "designation": "op",
    "managerId": 400,
    "deptId": 4444
  },
  {
    "id": 1002,
    "name": "op-3",
    "designation": "op",
    "managerId": 400,
    "deptId": 4444
  },
  {
    "id": 5050,
    "name": "hr-2",
    "designation": "hr",
    "managerId": 500,
    "deptId": 2222
  },
  {
    "id": 6000,
    "name": "hr-2",
    "designation": "hr",
    "managerId": 500,
    "deptId": 2222
  }
]
````

### To Fetch Employee by Manger Id
* http://localhost:8080/employees/manager/100
```` 
[
  {
    "id": 110,
    "name": "ajit",
    "designation": "it-lead",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 120,
    "name": "mulla",
    "designation": "it-admin",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 130,
    "name": "vishal",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 131,
    "name": "bbb",
    "designation": "sr-developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 133,
    "name": "ccc",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 135,
    "name": "ddd",
    "designation": "jr-developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 138,
    "name": "eee",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  }
]
````
* http://localhost:8080/employees/manager/110
```` 
[
  {
    "id": 121,
    "name": "aaa",
    "designation": "developer",
    "managerId": 110,
    "deptId": 1111
  }
]
````
### To fetch Employee by Department Id 
* http://localhost:8080/employees/department/101
```` 
[
  {
    "id": 1,
    "name": "radha",
    "designation": "founder-mgr",
    "managerId": 0,
    "deptId": 101
  }
]
````
* http://localhost:8080/employees/department/1111
```` 
[
  {
    "id": 100,
    "name": "adarsh",
    "designation": "it-mgr",
    "managerId": 1,
    "deptId": 1111
  },
  {
    "id": 110,
    "name": "ajit",
    "designation": "it-lead",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 120,
    "name": "mulla",
    "designation": "it-admin",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 121,
    "name": "aaa",
    "designation": "developer",
    "managerId": 110,
    "deptId": 1111
  },
  {
    "id": 130,
    "name": "vishal",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 131,
    "name": "bbb",
    "designation": "sr-developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 133,
    "name": "ccc",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 135,
    "name": "ddd",
    "designation": "jr-developer",
    "managerId": 100,
    "deptId": 1111
  },
  {
    "id": 138,
    "name": "eee",
    "designation": "developer",
    "managerId": 100,
    "deptId": 1111
  }
]
````
* http://localhost:8080/employees/department/2222
```` 
[
  {
    "id": 200,
    "name": "amit",
    "designation": "finance-mgr",
    "managerId": 1,
    "deptId": 2222
  },
  {
    "id": 5050,
    "name": "hr-2",
    "designation": "hr",
    "managerId": 500,
    "deptId": 2222
  },
  {
    "id": 6000,
    "name": "hr-2",
    "designation": "hr",
    "managerId": 500,
    "deptId": 2222
  }
]
````
* http://localhost:8080/employees/department/3333
```` 
[
  {
    "id": 220,
    "name": "111",
    "designation": "ca",
    "managerId": 200,
    "deptId": 3333
  },
  {
    "id": 230,
    "name": "222",
    "designation": "ca",
    "managerId": 200,
    "deptId": 3333
  },
  {
    "id": 500,
    "name": "hr-1",
    "designation": "hr-mgr",
    "managerId": 1,
    "deptId": 3333
  }
]
````
* http://localhost:8080/employees/department/4444
```` 
[
  {
    "id": 400,
    "name": "op-1",
    "designation": "op-mgr",
    "managerId": 1,
    "deptId": 4444
  },
  {
    "id": 1001,
    "name": "op-2",
    "designation": "op",
    "managerId": 400,
    "deptId": 4444
  },
  {
    "id": 1002,
    "name": "op-3",
    "designation": "op",
    "managerId": 400,
    "deptId": 4444
  }
]
````
### To find manager of the dept 
* http://localhost:8080/employee/manger/1111/-mgr
```` 

  {
    "id": 100,
    "name": "adarsh",
    "designation": "it-mgr",
    "managerId": 1,
    "deptId": 1111
  }
]
````
* http://localhost:8080/employee/manger/2222/-mgr
```` 
[
  {
    "id": 200,
    "name": "amit",
    "designation": "finance-mgr",
    "managerId": 1,
    "deptId": 2222
  }
]
````
### Tree Api
* http://localhost:8080/tree/{treeType}/{depth}
* http://localhost:8080/tree/EMP_TREE/10
```` 
{
  "data": {
    "id": 1,
    "name": "radha",
    "designation": "founder-mgr",
    "managerId": 0,
    "deptId": 101
  },
  "children": [
    {
      "data": {
        "id": 100,
        "name": "adarsh",
        "designation": "it-mgr",
        "managerId": 1,
        "deptId": 1111
      },
      "children": [
        {
          "data": {
            "id": 110,
            "name": "ajit",
            "designation": "it-lead",
            "managerId": 100,
            "deptId": 1111
          },
          "children": [
            {
              "data": {
                "id": 121,
                "name": "aaa",
                "designation": "developer",
                "managerId": 110,
                "deptId": 1111
              }
            }
          ]
        },
        {
          "data": {
            "id": 120,
            "name": "mulla",
            "designation": "it-admin",
            "managerId": 100,
            "deptId": 1111
          }
        },
        {
          "data": {
            "id": 130,
            "name": "vishal",
            "designation": "developer",
            "managerId": 100,
            "deptId": 1111
          }
        },
        {
          "data": {
            "id": 131,
            "name": "bbb",
            "designation": "sr-developer",
            "managerId": 100,
            "deptId": 1111
          }
        },
        {
          "data": {
            "id": 133,
            "name": "ccc",
            "designation": "developer",
            "managerId": 100,
            "deptId": 1111
          }
        },
        {
          "data": {
            "id": 135,
            "name": "ddd",
            "designation": "jr-developer",
            "managerId": 100,
            "deptId": 1111
          }
        },
        {
          "data": {
            "id": 138,
            "name": "eee",
            "designation": "developer",
            "managerId": 100,
            "deptId": 1111
          }
        }
      ]
    },
    {
      "data": {
        "id": 200,
        "name": "amit",
        "designation": "finance-mgr",
        "managerId": 1,
        "deptId": 2222
      },
      "children": [
        {
          "data": {
            "id": 220,
            "name": "111",
            "designation": "ca",
            "managerId": 200,
            "deptId": 3333
          }
        },
        {
          "data": {
            "id": 230,
            "name": "222",
            "designation": "ca",
            "managerId": 200,
            "deptId": 3333
          }
        }
      ]
    },
    {
      "data": {
        "id": 400,
        "name": "op-1",
        "designation": "op-mgr",
        "managerId": 1,
        "deptId": 4444
      },
      "children": [
        {
          "data": {
            "id": 1001,
            "name": "op-2",
            "designation": "op",
            "managerId": 400,
            "deptId": 4444
          }
        },
        {
          "data": {
            "id": 1002,
            "name": "op-3",
            "designation": "op",
            "managerId": 400,
            "deptId": 4444
          }
        }
      ]
    },
    {
      "data": {
        "id": 500,
        "name": "hr-1",
        "designation": "hr-mgr",
        "managerId": 1,
        "deptId": 3333
      },
      "children": [
        {
          "data": {
            "id": 5050,
            "name": "hr-2",
            "designation": "hr",
            "managerId": 500,
            "deptId": 2222
          }
        },
        {
          "data": {
            "id": 6000,
            "name": "hr-2",
            "designation": "hr",
            "managerId": 500,
            "deptId": 2222
          }
        }
      ]
    }
  ]
}
````
* http://localhost:8080/tree/DEPT_TREE/10
```` 
{
  "data": {
    "deptId": 101,
    "deptName": "management",
    "parentDeptId": 0
  },
  "children": [
    {
      "data": {
        "deptId": 1111,
        "deptName": "It",
        "parentDeptId": 101
      }
    },
    {
      "data": {
        "deptId": 2222,
        "deptName": "Finance",
        "parentDeptId": 101
      }
    },
    {
      "data": {
        "deptId": 3333,
        "deptName": "HR",
        "parentDeptId": 101
      }
    },
    {
      "data": {
        "deptId": 4444,
        "deptName": "OP",
        "parentDeptId": 101
      }
    }
  ]
}
````
* http://localhost:8080/tree/ALL_TREE/10
```` 

  "data": {
    "deptId": 101,
    "deptName": "management",
    "parentDeptId": 0,
    "employees": [
      {
        "id": 1,
        "name": "radha",
        "designation": "founder-mgr",
        "managerId": 0,
        "deptId": 101,
        "children": [
          {
            "id": 100,
            "name": "adarsh",
            "designation": "it-mgr",
            "managerId": 1,
            "deptId": 1111,
            "children": [
              {
                "id": 110,
                "name": "ajit",
                "designation": "it-lead",
                "managerId": 100,
                "deptId": 1111,
                "children": [
                  {
                    "id": 121,
                    "name": "aaa",
                    "designation": "developer",
                    "managerId": 110,
                    "deptId": 1111
                  }
                ]
              },
              {
                "id": 120,
                "name": "mulla",
                "designation": "it-admin",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 130,
                "name": "vishal",
                "designation": "developer",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 131,
                "name": "bbb",
                "designation": "sr-developer",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 133,
                "name": "ccc",
                "designation": "developer",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 135,
                "name": "ddd",
                "designation": "jr-developer",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 138,
                "name": "eee",
                "designation": "developer",
                "managerId": 100,
                "deptId": 1111
              }
            ]
          },
          {
            "id": 200,
            "name": "amit",
            "designation": "finance-mgr",
            "managerId": 1,
            "deptId": 2222,
            "children": [
              {
                "id": 220,
                "name": "111",
                "designation": "ca",
                "managerId": 200,
                "deptId": 3333
              },
              {
                "id": 230,
                "name": "222",
                "designation": "ca",
                "managerId": 200,
                "deptId": 3333
              }
            ]
          },
          {
            "id": 400,
            "name": "op-1",
            "designation": "op-mgr",
            "managerId": 1,
            "deptId": 4444,
            "children": [
              {
                "id": 1001,
                "name": "op-2",
                "designation": "op",
                "managerId": 400,
                "deptId": 4444
              },
              {
                "id": 1002,
                "name": "op-3",
                "designation": "op",
                "managerId": 400,
                "deptId": 4444
              }
            ]
          },
          {
            "id": 500,
            "name": "hr-1",
            "designation": "hr-mgr",
            "managerId": 1,
            "deptId": 3333,
            "children": [
              {
                "id": 5050,
                "name": "hr-2",
                "designation": "hr",
                "managerId": 500,
                "deptId": 2222
              },
              {
                "id": 6000,
                "name": "hr-2",
                "designation": "hr",
                "managerId": 500,
                "deptId": 2222
              }
            ]
          }
        ]
      }
    ]
  },
  "children": [
    {
      "data": {
        "deptId": 1111,
        "deptName": "It",
        "parentDeptId": 101,
        "employees": [
          {
            "id": 100,
            "name": "adarsh",
            "designation": "it-mgr",
            "managerId": 1,
            "deptId": 1111,
            "children": [
              {
                "id": 110,
                "name": "ajit",
                "designation": "it-lead",
                "managerId": 100,
                "deptId": 1111,
                "children": [
                  {
                    "id": 121,
                    "name": "aaa",
                    "designation": "developer",
                    "managerId": 110,
                    "deptId": 1111
                  }
                ]
              },
              {
                "id": 120,
                "name": "mulla",
                "designation": "it-admin",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 130,
                "name": "vishal",
                "designation": "developer",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 131,
                "name": "bbb",
                "designation": "sr-developer",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 133,
                "name": "ccc",
                "designation": "developer",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 135,
                "name": "ddd",
                "designation": "jr-developer",
                "managerId": 100,
                "deptId": 1111
              },
              {
                "id": 138,
                "name": "eee",
                "designation": "developer",
                "managerId": 100,
                "deptId": 1111
              }
            ]
          }
        ]
      }
    },
    {
      "data": {
        "deptId": 2222,
        "deptName": "Finance",
        "parentDeptId": 101,
        "employees": [
          {
            "id": 200,
            "name": "amit",
            "designation": "finance-mgr",
            "managerId": 1,
            "deptId": 2222,
            "children": [
              {
                "id": 220,
                "name": "111",
                "designation": "ca",
                "managerId": 200,
                "deptId": 3333
              },
              {
                "id": 230,
                "name": "222",
                "designation": "ca",
                "managerId": 200,
                "deptId": 3333
              }
            ]
          }
        ]
      }
    },
    {
      "data": {
        "deptId": 3333,
        "deptName": "HR",
        "parentDeptId": 101,
        "employees": [
          {
            "id": 500,
            "name": "hr-1",
            "designation": "hr-mgr",
            "managerId": 1,
            "deptId": 3333,
            "children": [
              {
                "id": 5050,
                "name": "hr-2",
                "designation": "hr",
                "managerId": 500,
                "deptId": 2222
              },
              {
                "id": 6000,
                "name": "hr-2",
                "designation": "hr",
                "managerId": 500,
                "deptId": 2222
              }
            ]
          }
        ]
      }
    },
    {
      "data": {
        "deptId": 4444,
        "deptName": "OP",
        "parentDeptId": 101,
        "employees": [
          {
            "id": 400,
            "name": "op-1",
            "designation": "op-mgr",
            "managerId": 1,
            "deptId": 4444,
            "children": [
              {
                "id": 1001,
                "name": "op-2",
                "designation": "op",
                "managerId": 400,
                "deptId": 4444
              },
              {
                "id": 1002,
                "name": "op-3",
                "designation": "op",
                "managerId": 400,
                "deptId": 4444
              }
            ]
          }
        ]
      }
    }
  ]
}
````