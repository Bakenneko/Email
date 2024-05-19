package org.example.email.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.email.views.Views;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Admin.class})
    private int id;

    @NotEmpty
    @Length(min = 3, max = 12, message = "Увага ім'я")
    @JsonView({Views.Admin.class, Views.Client.class})
    private String name;
    private boolean isActivated = false;

    @JsonView({Views.Admin.class, Views.Client.class})
    private String email;

    public Customer(String name) {this.name = name;}

}
