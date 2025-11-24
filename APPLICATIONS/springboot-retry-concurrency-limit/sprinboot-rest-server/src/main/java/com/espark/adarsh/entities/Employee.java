package com.espark.adarsh.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee{

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
   String name;
   String email;

}
