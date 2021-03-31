-- create table for employees
create table employee(
    "empid"         number not null enable,
    "name"          varchar2(10) not null,
    "role"          varchar2(10) not null,
    "city"          varchar2(10) not null,
    "country"       varchar2(10) not null,
    primary key ("empid")
);

-- create procedure for inserting employees
create or replace procedure insert_employee(
    in_id           in  employee.empid%type,
    in_name         in  employee.name%type,
    in_role         in  employee.role%type,
    in_city         in  employee.city%type,
    in_country      in  employee.country%type,    
    out_result      out varchar2
) as
begin
    insert into employee(empid, name, role, city, country)
        value(in_id, in_name, in_role, in_city, in_country);
    commit;
    out_result := 'true';
exception
    when others then
        out_result := 'false';
        rollback;
end;
/

-- create procedure for getting employee by id
create or replace procedure get_employee(
    in_id           in  employee.empid%type,
    out_name        out employee.name%type,
    out_role        out employee.role%type,
    out_city        out employee.city%type,
    out_country     out employee.country%type,
) as
begin
    select name, role, city, country
        into out_name, out_role, out_city, out_country
        from employee
        where empid = in_id;
end;
/

-- create procedure for getting multiply employee by role
create or replace procedure get_employee_by_role(
    in_role         in  employee.role%type,
    out_cursor_emp  out sys_refcursor
) as
begin
    open out_cursor_emp for
        select empid, name, city, country
        from employee
        where role = in_role;
end;
/

-- create employee type
create or replace type employee_obj as object(
    empid       number,
    name        varchar2(10),
    role        varchar2(10),
    city        varchar2(10),
    country     varchar2(10)
);

-- procedure rewrited to use employe type
-- create procedure for inserting employees
create or replace procedure insert_employee_obj(
    in_emp_obj      in  employee_obj,
    out_result      out varchar2
) as
begin
    insert into employee(empid, name, role, city, country)
        value(in_emp_obj.empid, in_emp_obj.name, in_emp_obj.role,
            in_emp_obj.city, in_emp_obj.country);
    commit;

    out_result := 'true';
exception
    when others then
        out_result := 'false';
        rollback;
end;
/
