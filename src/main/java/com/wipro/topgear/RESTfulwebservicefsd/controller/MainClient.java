package com.wipro.topgear.RESTfulwebservicefsd.controller;

import com.wipro.topgear.RESTfulwebservicefsd.entity.Employee;
import com.wipro.topgear.RESTfulwebservicefsd.entity.Movie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MainClient {


    public String creditNoValidator(String ccNo) {
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/ccard/" + ccNo, HttpMethod.GET, null, String.class);
        return response.getBody();
    }


    public String dateAssignment(String dob, String ageOnDate) {
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/dob/" + dob+"/"+ageOnDate, HttpMethod.GET, null, String.class);
        return response.getBody();
    }


    public String squareNumber(String num) {
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/squareNo/" + num, HttpMethod.GET, null, String.class);
        return response.getBody();
    }


    public String toLowerCase(String name) {
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/toLowerCase/" + name, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    public String simpleCalculator(String exp) {
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/calc/" + exp, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    public String saveEmployee(Employee emp) {
        String response = new RestTemplate().postForObject("http://localhost:8080/saveEmployee", emp, String.class);
        return response;
    }


    public Employee findEmployee(Long id) {
        ResponseEntity<Employee> response = new RestTemplate().exchange("http://localhost:8080/getEmployee/" + id, HttpMethod.GET, null, Employee.class);
        return response.getBody();
    }

    public String deleteEmployee(Long id) {
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/deleteEmployee/" + id, HttpMethod.DELETE, null, String.class);
        return response.getBody();
    }

    public List<Movie> getMovieListinTextXML(){
        ResponseEntity<List<Movie>> response = new RestTemplate().exchange("http://localhost:8080/getMovieListinTextXML", HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>(){});
        return response.getBody();
    }


    public List<Movie> getMovieListinApplicationXML(){
        ResponseEntity<List<Movie>> response = new RestTemplate().exchange("http://localhost:8080/getMovieListinApplicationXML", HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>(){});
        return response.getBody();
    }


    public static void main(String[] args) {

        System.out.println(new MainClient().creditNoValidator("1234"));

        System.out.println(new MainClient().dateAssignment("1995-11-28", "2015-12-25"));

        System.out.println(new MainClient().squareNumber("1234"));

        System.out.println(new MainClient().toLowerCase("FsFdTRdRDTRrd"));


        System.out.println(new MainClient().simpleCalculator("23*42"));


        Employee emp = new Employee();
         emp.setEmpName("Abinit1234"); emp.setEmpDesignation("PE1");

        System.out.println(new MainClient().saveEmployee(emp));

        System.out.println(new MainClient().findEmployee(2L));

       // System.out.println(new MainClient().deleteEmployee(2L));

        System.out.println(new MainClient().getMovieListinTextXML());

        System.out.println(new MainClient(). getMovieListinApplicationXML());




    }
}
