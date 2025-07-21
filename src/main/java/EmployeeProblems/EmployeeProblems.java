package EmployeeProblems;

import model.Employee;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//https://medium.com/@veenaraofr/java8-stream-api-commonly-asked-questions-about-employee-highest-salary-99c21cec4d98
public class EmployeeProblems {
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


            //2. Group the Employees by age.

        Map<Integer,List<Employee>> age = empList.stream().collect(Collectors.groupingBy(Employee::getAge));


        //3. Find the count of male and female employees present in the organization.
        //{F=5, M=4}
        Map<String, Long> collect1 = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        //System.out.println(collect1);

        //4. Print the names of all departments in the organization.
        List<String> collect2 = empList.stream().map(x -> x.getDeptName()).collect(Collectors.toList());
        //empList.stream().map(x -> x.getDeptName()).forEach(System.out::println); if we are printing and not collecting
        //System.out.println(collect2);

        //5. Print employee details whose age is greater than 18.
        List<String> collect3 = empList.stream().filter(employee -> employee.getAge() > 18).map(Employee::getName).collect(Collectors.toList());
        //collect3.forEach(System.out::println);
        //To get list of employees instead of integers
        List<Employee> list = empList.stream().filter(e -> e.getAge() > 18).collect(Collectors.toList());
        //System.out.println(list);

        //6. Find maximum age of employee.
        OptionalInt max = empList.stream().mapToInt(emp -> emp.getAge()).max();
        //System.out.println(max);

        //7. Print Average age of Male and Female Employees.
        //{F=27.4, M=26.25}
        Map<String, Double> avgAge = empList.stream().collect(Collectors.groupingBy
                (Employee::getGender,Collectors.averagingInt(Employee::getAge)));
        //System.out.println("Average age of Male and Female Employees:: " + avgAge);

        //8. Print the number of employees in each department.
        //{HR=4, IT=5}
        Map<String, Long> collect4 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        //System.out.println(collect4);

        //9. Find oldest employee.

        Optional<Employee> ageint=  empList.stream().max(Comparator.comparing(Employee::getAge));
        //Optional<Integer> ageint=  empList.stream().max(Comparator.comparing(employee -> employee.getAge())).map(Employee::getAge);
        //System.out.println(ageint.get());

        //10. Find youngest female employee.
        Optional<Employee> minAge = empList.stream().filter(employee -> employee.getGender()=="F").min(Comparator.comparing(Employee::getAge));
        //System.out.println(minAge.get());

        //11. Find employees whose age is greater than 30 and less than 30.

        Map<Boolean, List<Employee>> collect5 = empList.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() > 30));

        for(Map.Entry<Boolean,List<Employee>> map: collect5.entrySet()){
            if(map.getKey()==true){
                //System.out.println(map.getValue());
            }else {
               // System.out.println(map.getValue());
            }
        }

        List<Employee> over30 = collect5.get(true);
        List<Employee> underOrEqual30 = collect5.get(false);

        //System.out.println("Employees over 30:");
        //over30.forEach(System.out::println);

        //System.out.println("Employees 30 or under:");
        //underOrEqual30.forEach(System.out::println);



        //12. Find the department name which has the highest number of employees.

        Map.Entry<String, Long> stringLongEntry = empList.stream().collect(Collectors.groupingBy(employee -> employee.getDeptName(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();
        //System.out.println(stringLongEntry);



        //13. Find if there any employees from HR Department.
        Optional<Employee> any = empList.stream().filter(employee -> employee.getDeptName() == "HR").findAny();
        //System.out.println(any.get());


        //14. Find the department names that these employees work for, where the number of employees in the department is over 3.
        //O/P [HR=4, IT=5]
        List<Map.Entry<String, Long>> collect6 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet().stream().filter(emp -> emp.getValue() > 3).collect(Collectors.toList());
        //System.out.println(collect6);

        //15 . Find distinct department names that employees work for.
        Stream<String> distinct = empList.stream().map(Employee::getDeptName).distinct();
        //distinct.forEach(System.out::println);

        //16. Find all employees who lives in ‘Blore’ city, sort them by their name and print the names of employees.
        Stream<Employee> sorted = empList.stream().filter(employee -> employee.getCity() == "Blore").sorted(Comparator.comparing(Employee::getName));
        Stream<String> sorted2 = empList.stream().filter(employee -> employee.getCity() == "Blore").sorted(Comparator.comparing(Employee::getName)).map(Employee::getName);

        //sorted.forEach(System.out::println);

        //17. No of employees in the organisation.
        long collect7 = empList.stream().count();
        //System.out.println(collect7);

        //18. Find employee count in every department
        Map<String, Long> femp = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.counting()));
        //femp.entrySet().stream().forEach(System.out::println);

        //19. Find the department which has the highest number of employees.
        Optional<Map.Entry<String, Long>> max1 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue());
        //System.out.println(max1.get());

        //20. Sorting a Stream by age and name fields.
        Comparator<Employee> byAge = Comparator.comparingInt(Employee::getAge);
        Comparator<Employee> byName = Comparator.comparing(Employee::getName);
        Stream<Employee> sorted1 = empList.stream().sorted(byAge.thenComparing(byName));
        //sorted1.forEach(System.out::println);

        //21. Highest experienced employees in the organization.
        Optional<Employee> first = empList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
        //System.out.println(first.get());


        //22. Print average and total salary of the organization.
        DoubleSummaryStatistics collect8 = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        //System.out.println("avg "+collect8.getAverage()+" sum "+collect8.getSum());


        //23. Print Average salary of each department.
        Map<String, DoubleSummaryStatistics> collect9 = empList.stream().collect(Collectors.groupingBy((Employee::getDeptName), Collectors.summarizingDouble(Employee::getSalary)));
        //collect9.entrySet().stream().forEach(d->d.getKey());//TODO need to check, how to print key and value


        //24. Find Highest salary in the organisation.
        Optional<Employee> first1 = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).findFirst();
        //System.out.println(first1.get());


        //25. Find Second Highest salary in the organisation.
        Optional<Employee> first2 = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst();
        //System.out.println(first2.get());

        //26. Nth Highest salary.
        int n = 10;
        empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(n-1).findFirst();

        //explain what exactly happens in grouby in thi s empList.stream().collect(Collectors.groupingBy(Employee::getGender,
        //                Collectors.counting())); explain downstream collector
        //27. Find highest paid salary in the organisation based on gender.
        //{F=Optional[Employee1{id=5, name='ijk', age=22, salary=150, gender='F', deptName='IT', city='Noida', yearOfJoining='2013'}],
        // M=Optional[Employee1{id=9, name='stv', age=25, salary=160, gender='M', deptName='IT', city='Blore', yearOfJoining='2010'}]}
        Map<String, Optional<Employee>> highestPaidMFEmployee = empList.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.maxBy((t1, t2) -> (int) (t1.getSalary() - t2.getSalary()))));
       // System.out.println("Highest paid male and female employee : " + highestPaidMFEmployee);


        //28. Find lowest paid salary in the organisation based on gender
        //Lowest salary based on gender{F=Optional[Employee1{id=2, name='xyz', age=29, salary=120, gender='F', deptName='HR', city='Hyderabad', yearOfJoining='2015'}],
        // M=Optional[Employee1{id=3, name='efg', age=30, salary=115, gender='M', deptName='HR', city='Chennai', yearOfJoining='2014'}]}

        Map<String, Optional<Employee>> collect10 = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.minBy((t1, t2) -> (int) (t1.getSalary() - t2.getSalary()))));
        //System.out.println("Lowest salary based on gender"+collect10);


        //29. Sort the employees salary in the organisation in ascending order
        //[Employee1{id=3, name='efg', age=30, salary=115, gender='M', deptName='HR', city='Chennai', yearOfJoining='2014'}, Employee1{id=2, name='xyz', age=29, salary=120, gender='F', deptName='HR', city='Hyderabad', yearOfJoining='2015'}, Employee1{id=1, name='abc', age=28, salary=123, gender='F', deptName='HR', city='Blore', yearOfJoining='2020'}, Employee1{id=4, name='def', age=32, salary=125, gender='F', deptName='HR', city='Chennai', yearOfJoining='2013'}, Employee1{id=7, name='uvw', age=26, salary=130, gender='F', deptName='IT', city='Pune', yearOfJoining='2016'}, Employee1{id=6, name='mno', age=27, salary=140, gender='M', deptName='IT', city='Gurugram', yearOfJoining='2017'}, Employee1{id=8, name='pqr', age=23, salary=145, gender='M', deptName='IT', city='Trivandam', yearOfJoining='2015'}, Employee1{id=5, name='ijk', age=22, salary=150, gender='F', deptName='IT', city='Noida', yearOfJoining='2013'}, Employee1{id=9, name='stv', age=25, salary=160, gender='M', deptName='IT', city='Blore', yearOfJoining='2010'}]

        List<Employee> employees = empList.stream().sorted(Comparator.comparingLong(Employee::getSalary)).toList();
        //System.out.println(employees);


        //30. Sort the employees salary in the organisation in descending order.
       // [Employee1{id=3, name='efg', age=30, salary=115, gender='M', deptName='HR', city='Chennai', yearOfJoining='2014'}, Employee1{id=2, name='xyz', age=29, salary=120, gender='F', deptName='HR', city='Hyderabad', yearOfJoining='2015'}, Employee1{id=1, name='abc', age=28, salary=123, gender='F', deptName='HR', city='Blore', yearOfJoining='2020'}, Employee1{id=4, name='def', age=32, salary=125, gender='F', deptName='HR', city='Chennai', yearOfJoining='2013'}, Employee1{id=7, name='uvw', age=26, salary=130, gender='F', deptName='IT', city='Pune', yearOfJoining='2016'}, Employee1{id=6, name='mno', age=27, salary=140, gender='M', deptName='IT', city='Gurugram', yearOfJoining='2017'}, Employee1{id=8, name='pqr', age=23, salary=145, gender='M', deptName='IT', city='Trivandam', yearOfJoining='2015'}, Employee1{id=5, name='ijk', age=22, salary=150, gender='F', deptName='IT', city='Noida', yearOfJoining='2013'}, Employee1{id=9, name='stv', age=25, salary=160, gender='M', deptName='IT', city='Blore', yearOfJoining='2010'}]

        List<Employee> employees2 = empList.stream().sorted(Comparator.comparingLong(Employee::getSalary)).toList();
        //System.out.println(employees2);


        //31. Highest salary based on department.
        //{HR=Optional[Employee1{id=4, name='def', age=32, salary=125, gender='F', deptName='HR', city='Chennai', yearOfJoining='2013'}],
        // IT=Optional[Employee1{id=9, name='stv', age=25, salary=160, gender='M', deptName='IT', city='Blore', yearOfJoining='2010'}]}

        Map<String, Optional<Employee>> collect11 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.collectingAndThen(Collectors.toList(),
                listm -> listm.stream().max(Comparator.comparingDouble(Employee::getSalary)))));
        //System.out.println(collect11);

        //32. Print list of employee’s second highest record based on department
        Map<String, Optional<Employee>> collect13 = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        employees1 -> employees1.stream().
                                sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst()
                )));
        //System.out.println(collect13);


        //33. Sort the employees salary in each department in ascending order
//        HR
//                [Employee1{id=3, name='efg', age=30, salary=115, gender='M', deptName='HR', city='Chennai', yearOfJoining='2014'}, Employee1{id=2, name='xyz', age=29, salary=120, gender='F', deptName='HR', city='Hyderabad', yearOfJoining='2015'}, Employee1{id=1, name='abc', age=28, salary=123, gender='F', deptName='HR', city='Blore', yearOfJoining='2020'}, Employee1{id=4, name='def', age=32, salary=125, gender='F', deptName='HR', city='Chennai', yearOfJoining='2013'}]
//        IT
//                [Employee1{id=7, name='uvw', age=26, salary=130, gender='F', deptName='IT', city='Pune', yearOfJoining='2016'}, Employee1{id=6, name='mno', age=27, salary=140, gender='M', deptName='IT', city='Gurugram', yearOfJoining='2017'}, Employee1{id=8, name='pqr', age=23, salary=145, gender='M', deptName='IT', city='Trivandam', yearOfJoining='2015'}, Employee1{id=5, name='ijk', age=22, salary=150, gender='F', deptName='IT', city='Noida', yearOfJoining='2013'}, Employee1{id=9, name='stv', age=25, salary=160, gender='M', deptName='IT', city='Blore', yearOfJoining='2010'}]

        Map<String, Stream<Employee>> collect12 = empList.stream().collect(Collectors.groupingBy(
                Employee::getDeptName, Collectors.collectingAndThen(Collectors.toList()
                        , emp -> emp.stream().sorted(Comparator.comparingDouble(Employee::getSalary)))));

        collect12.forEach((k,v)-> {  //to print a map of value as stream
           // System.out.println(k);
            //System.out.println(v.collect(Collectors.toList())); //stream to list conversion
        });

        //34. Sort the employees salary in each department in descending order

//        HR
//                [Employee1{id=4, name='def', age=32, salary=125, gender='F', deptName='HR', city='Chennai', yearOfJoining='2013'}, Employee1{id=1, name='abc', age=28, salary=123, gender='F', deptName='HR', city='Blore', yearOfJoining='2020'}, Employee1{id=2, name='xyz', age=29, salary=120, gender='F', deptName='HR', city='Hyderabad', yearOfJoining='2015'}, Employee1{id=3, name='efg', age=30, salary=115, gender='M', deptName='HR', city='Chennai', yearOfJoining='2014'}]
//        IT
//                [Employee1{id=9, name='stv', age=25, salary=160, gender='M', deptName='IT', city='Blore', yearOfJoining='2010'}, Employee1{id=5, name='ijk', age=22, salary=150, gender='F', deptName='IT', city='Noida', yearOfJoining='2013'}, Employee1{id=8, name='pqr', age=23, salary=145, gender='M', deptName='IT', city='Trivandam', yearOfJoining='2015'}, Employee1{id=6, name='mno', age=27, salary=140, gender='M', deptName='IT', city='Gurugram', yearOfJoining='2017'}, Employee1{id=7, name='uvw', age=26, salary=130, gender='F', deptName='IT', city='Pune', yearOfJoining='2016'}]

        Map<String, Stream<Employee>> collect14 = empList.stream().collect(Collectors.groupingBy(
                Employee::getDeptName,
                Collectors.collectingAndThen(
                        Collectors.toList(), x -> x.stream().sorted(
                                Comparator.comparingDouble(Employee::getSalary).reversed()))));
        collect14.forEach((k,v)->{
            //System.out.println(k);
            //System.out.println(v.collect(Collectors.toList()));
        });

    }
}
