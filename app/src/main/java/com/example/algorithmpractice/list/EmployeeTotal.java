package com.example.algorithmpractice.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by hufei on 2021/5/1.
 * 654267767@qq.com
 * des:
 */
public class EmployeeTotal {

    Map<Integer, Employee> map = new HashMap<Integer, Employee>();

    /**
     *  深度优先
     * @param employees
     * @param id
     * @return
     */
    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }

    public int dfs(int id) {
        Employee employee = map.get(id);
        int total = employee.importance;
        List<Integer> subordinates = employee.subordinates;
        for (int subId : subordinates) {
            total += dfs(subId);
        }
        return total;
    }


    /**
     *  广度优先
     * @param employees
     * @param id
     * @return
     */
    public static int getImportance2(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for(int i=0; i < employees.size(); i++) {
            map.put(employees.get(i).id, employees.get(i));
        }
        int ans = 0;
        Queue<Employee> que = new LinkedList<>();

        que.offer(map.get(id));
        while(!que.isEmpty()) {
            Employee emp = que.poll();
            if(emp != null) {
                ans += emp.importance;
                List<Integer> child = emp.subordinates;
                if(null == child) {
                    continue;
                }
                for(int i = 0; i <child.size();i++) {
                    que.offer(map.get(child.get(i)));
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.id = 1;
        employee.importance = 5;
        List<Integer> subs = new ArrayList<>();
        subs.add(2);
        subs.add(3);
        employee.subordinates = subs;

        Employee employee2 = new Employee();
        employee2.id = 2;
        employee2.importance = 3;
        List<Integer> subs2 = new ArrayList<>();
        employee2.subordinates = subs2;


        Employee employee3 = new Employee();
        employee3.id = 3;
        employee3.importance = 3;
        List<Integer> subs3 = new ArrayList<>();
        employee3.subordinates = subs3;

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);
        employees.add(employee3);

//        System.out.println("结果:" + EmployeeTotal.getImportance(employees, 1));
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", importance=" + importance +
                ", subordinates=" + subordinates +
                '}';
    }
};
