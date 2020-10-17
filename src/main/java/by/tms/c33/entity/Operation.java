package by.tms.c33.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double num1;
    private double num2;
    private String type;
    private double result;


    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", num1=" + num1 +
                ", num2=" + num2 +
                ", type='" + type + '\'' +
                ", result=" + result +
                '}';
    }
}
