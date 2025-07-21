package EmployeeProblems;

import model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeJavaQuestions {

    public static void main(String[] args) {
        //i/p list of employee
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
        empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
        empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));

        empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
        empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
        empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
        empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));
        empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));

        //1.Group the Employees by city.
        //GroupingBY Collectors.groupingBy Just 1 Input in groupingBy - RETURNS Map<String, List<Employee>>

        // Group employees by city
        // {Chennai=[Employee1{id=3, name='efg', age=30, salary=115, gender='M', deptName='HR', city='Chennai', yearOfJoining='2014'}, Employee1{id=4, name='def', age=32, salary=125, gender='F', deptName='HR', city='Chennai', yearOfJoining='2013'}],
        // Trivandam=[Employee1{id=8, name='pqr', age=23, salary=145, gender='M', deptName='IT', city='Trivandam', yearOfJoining='2015'}],
        // Pune=[Employee1{id=7, name='uvw', age=26, salary=130, gender='F', deptName='IT', city='Pune', yearOfJoining='2016'}], Noida=[Employee1{id=5, name='ijk', age=22, salary=150, gender='F', deptName='IT', city='Noida', yearOfJoining='2013'}],
        // Blore=[Employee1{id=1, name='abc', age=28, salary=123, gender='F', deptName='HR', city='Blore', yearOfJoining='2020'}, Employee1{id=9, name='stv', age=25, salary=160, gender='M', deptName='IT', city='Blore', yearOfJoining='2010'}],
        // Gurugram=[Employee1{id=6, name='mno', age=27, salary=140, gender='M', deptName='IT', city='Gurugram', yearOfJoining='2017'}],
        // Hyderabad=[Employee1{id=2, name='xyz', age=29, salary=120, gender='F', deptName='HR', city='Hyderabad', yearOfJoining='2015'}]}

        Map<String, List<Employee>> collect = empList.stream().collect(Collectors.groupingBy(Employee::getCity));
        //x->x.getCity()
        //System.out.println(collect);

        //2.Group the Employees by age.
        //{32=[Employee1{id=4, name='def', age=32, salary=125, gender='F', deptName='HR', city='Chennai', yearOfJoining='2013'}], 22=[Employee1{id=5, name='ijk', age=22, salary=150, gender='F', deptName='IT', city='Noida', yearOfJoining='2013'}], 23=[Employee1{id=8, name='pqr', age=23, salary=145, gender='M', deptName='IT', city='Trivandam', yearOfJoining='2015'}], 25=[Employee1{id=9, name='stv', age=25, salary=160, gender='M', deptName='IT', city='Blore', yearOfJoining='2010'}], 26=[Employee1{id=7, name='uvw', age=26, salary=130, gender='F', deptName='IT', city='Pune', yearOfJoining='2016'}], 27=[Employee1{id=6, name='mno', age=27, salary=140, gender='M', deptName='IT', city='Gurugram', yearOfJoining='2017'}], 28=[Employee1{id=1, name='abc', age=28, salary=123, gender='F', deptName='HR', city='Blore', yearOfJoining='2020'}], 29=[Employee1{id=2, name='xyz', age=29, salary=120, gender='F', deptName='HR', city='Hyderabad', yearOfJoining='2015'}], 30=[Employee1{id=3, name='efg', age=30, salary=115, gender='M', deptName='HR', city='Chennai', yearOfJoining='2014'}]}

        Map<Integer,List<Employee>> age = empList.stream().collect(Collectors.groupingBy(Employee::getAge));

        //GroupingBY two inputs, Collectors.counting() RETURNS Map<String, Long>
        //3.Find the count of male and female employees present in the organization.
        //{F=5, M=4}
        Map<String, Long> collect1 = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        //System.out.println(collect1);

        //Print the number of employees in each department.
        //{HR=4, IT=5}
        Map<String, Long> collect4 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        //System.out.println(collect4);

        //GroupingBY Collectors.averagingInt
        // Print Average age of Male and Female Employees.
        //{F=27.4, M=26.25}
        Map<String, Double> avgAge = empList.stream().collect(Collectors.groupingBy
                (Employee::getGender,Collectors.averagingInt(Employee::getAge)));
        //System.out.println("Average age of Male and Female Employees:: " + avgAge);

        //Comparator
        //empList.stream().max()
        //Find oldest employee.
        Optional<Employee> ageint=  empList.stream().max(Comparator.comparing(Employee::getAge));
        //Optional<Integer> ageint=  empList.stream().max(Comparator.comparing(employee -> employee.getAge())).map(Employee::getAge);
        //System.out.println(ageint.get());


        //Map
        // Print the names of all departments in the organization.
        List<String> collect2 = empList.stream().map(x -> x.getDeptName()).collect(Collectors.toList());
        //empList.stream().map(x -> x.getDeptName()).forEach(System.out::println); if we are printing and not collecting
        //System.out.println(collect2);



        //Filter
        //To get list of employees instead of integers
        List<Employee> list = empList.stream().filter(e -> e.getAge() > 18).collect(Collectors.toList());
        //System.out.println(list);

        //Map and Filter
        List<String> collect3 = empList.stream().filter(employee -> employee.getAge() > 18).map(Employee::getName).collect(Collectors.toList());
        //collect3.forEach(System.out::println);

        //mapToInt
        // Find maximum age of employee.
        OptionalInt max = empList.stream().mapToInt(emp -> emp.getAge()).max();
        //System.out.println(max);



//Test my skills
        Map<String, Double> avgAgee = empList.stream().collect(Collectors.groupingBy
                (Employee::getGender,Collectors.averagingInt(
                        Employee::getAge)));
        System.out.println(avgAgee);


    }
}
//Notes
//To Convert Stream to List we use collect.(Collectors.toList()))
