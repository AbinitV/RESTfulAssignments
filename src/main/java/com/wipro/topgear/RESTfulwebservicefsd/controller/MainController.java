package com.wipro.topgear.RESTfulwebservicefsd.controller;

import com.wipro.topgear.RESTfulwebservicefsd.entity.Employee;
import com.wipro.topgear.RESTfulwebservicefsd.entity.Movie;
import com.wipro.topgear.RESTfulwebservicefsd.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.*;

@RestController
public class MainController {

    @Autowired
    private EmployeeRepo employeeRepo;

    static List<Movie> movieList = new ArrayList<>();

    private void initMovieList()
    {
        Movie movie1 = new Movie("1","movie1","actor1",1200000F);
        movieList.add(movie1);
        Movie movie2 = new Movie("2","movie2","actor2",1200000F);
        movieList.add(movie2);
        Movie movie3 = new Movie("3","movie3","actor3",1200000F);
        movieList.add(movie3);
        Movie movie4 = new Movie("4","movie4","actor4",1200000F);
        movieList.add(movie4);
        Movie movie5 = new Movie("5","movie5","actor5",1200000F);
        movieList.add(movie5);
    }

    @RequestMapping(value = "/ccard/{ccnumber}", method = RequestMethod.GET, produces = TEXT_PLAIN_VALUE)
    public String creditNoValidator(@PathVariable("ccnumber") final String cNumber){
        Long cNo = 0L;
        try{
            cNo = Long.parseLong(cNumber);
        }
        catch(NumberFormatException e){
            return "Error";
        }
        if(cNo%2==0)
        return "Valid Card Number!!!";
        else
            return "Invalid Card No.!!!";

    }

    @RequestMapping(value="/dob/{dateOfBirth}/{ageOnDate}", method = RequestMethod.GET)
    public String dateAssignment(@PathVariable("dateOfBirth") final String dateOfBirth, @PathVariable("ageOnDate") final String ageOnDate){
        Date dateOfBirth1 = null;
        Date ageOnDate1 = null;
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         dateOfBirth1 = sdf.parse(dateOfBirth);
         ageOnDate1 = sdf.parse(ageOnDate);
    }catch(Exception e){
        e.printStackTrace();
    }
        return String.valueOf(ageOnDate1.getYear()-dateOfBirth1.getYear());
    }


    @RequestMapping(value = "/squareNo/{num}", method = RequestMethod.GET)
    public String squareNumber(@PathVariable final String num){

        return "Square of "+ num + " is:" +Integer.parseInt(num)*Integer.parseInt(num);
    }

    @RequestMapping(value = "/toLowerCase/{name}", method = RequestMethod.GET)
    public String toLowerCase(@PathVariable final String name){

        return "Your Name in Lower Case:" + name.toLowerCase();
    }

    @RequestMapping(value="/calc/{exp}", method = RequestMethod.GET)
    public String simpleCalculator(@PathVariable final String exp){
        int operPos = 0;
        char oper = '\u0000';
        for(operPos=0;operPos<exp.length();operPos++){
            // have used ^ for division
            if(exp.charAt(operPos)=='+' || exp.charAt(operPos)=='-' || exp.charAt(operPos)=='^' || exp.charAt(operPos)=='*'){
                oper = exp.charAt(operPos);
                break;
            }
        }
        int num1 = Integer.parseInt(exp.substring(0,operPos));
        int num2 = Integer.parseInt(exp.substring(operPos+1));
        switch(oper){
            case '+':
                return String.valueOf(num1 + num2);

            case '-':
                return String.valueOf(num1 - num2);

            case '^':
                return String.valueOf(num1 / num2);

            case '*':
                return String.valueOf(num1 * num2);

            default:
                return "Unsupported operator!!";
        }
    }



    @RequestMapping(value="/saveEmployee", method = RequestMethod.POST)
    public String saveEmployee(@RequestBody Employee employee){
        if(employee!=null){
            employeeRepo.save(employee);
        }
        return "Success";
    }


    @RequestMapping(value="/getEmployee/{id}", method = RequestMethod.GET)
    public Employee findEmployee(@PathVariable("id") Long id){
       Optional<Employee> emp = employeeRepo.findById(id);
       if(emp.isPresent())
           return emp.get();
       return null;
    }

    @RequestMapping(value="/deleteEmployee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Long id){
         employeeRepo.deleteById(id);
        return "Employee Deleted";
    }


    @RequestMapping(value="/getMovieListinTextXML", method = RequestMethod.GET, produces = "text/xml")
    public List<Movie> getMovieListinTextXML(){
        initMovieList();
        return movieList;
    }

    @RequestMapping(value="/getMovieListinApplicationXML", method = RequestMethod.GET, produces = "application/xml")
    public List<Movie> getMovieListinApplicationXML(){
        initMovieList();
        return movieList;
    }

    @RequestMapping(value="/getMovieListinJSON", method = RequestMethod.GET, produces = "application/json")
    public List<Movie> getMovieListinJSON(){
        initMovieList();
        return movieList;
    }


    @RequestMapping(value="/getMovieListinJSON/{movieId}", method = RequestMethod.GET, produces = "application/json")
    public Movie getMovieListinJSON(@PathVariable("movieId") final String movieId){
        initMovieList();

        for (Movie movie: movieList
             ) {
            if(movie.getMovieId().equals(movieId))
                return movie;
        }
        return null;
    }


}
