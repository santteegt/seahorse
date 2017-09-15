# Copyright (c) 2015, CodiLime Inc.

*** Settings ***
Suite Setup       Connect To Database    pymysql    mysql    system_tests    pass    spark-cluster-1    3306
Suite Teardown    Disconnect From Database
Library           DatabaseLibrary
Library           OperatingSystem

Library    OperatingSystem
Library    Collections
Library    ../lib/HdfsClient.py
Library    ../lib/S3Client.py
Library    ../lib/WorkflowExecutorClient.py

*** Test Cases ***
Read Write MySQL
    ${dir} =    Set Variable    resources/mySQLTests/readWriteMySQL/
    Execute Sql Script    ${dir}fixture.sql

    Remove Directory    readWriteMySQLOutput    recursive=yes
    Create Output Directory    readWriteMySQLOutput
    Run Workflow Local    ${dir}workflow.json    --jars ${dir}../mysql-connector-java-5.1.36.jar

    Check Execution Status
    Check Report    ${dir}expectedReportPattern.json
    @{queryResultsIn} =    Query   SELECT * FROM read_write_mysql_in ORDER BY string_col;
    @{queryResultsOut} =    Query   SELECT * FROM read_write_mysql_out ORDER BY string_col;
    Should Be Equal As Strings    ${queryResultsIn}    ${queryResultsOut}

    Clean Output Directory
    Execute Sql Script    ${dir}cleanup.sql