@echo off
echo CODEGEN VERSION
set CODEGEN=swagger-codegen-cli-2.4.16.jar
echo version	: %CODEGEN%
echo url		: %1
echo output dir 	: %2
echo 'make client stub'
java -Dmodels -Dapis -jar %3/app/libs/%CODEGEN% generate -i %1 -l java -o %2 --library retrofit2

exit /b 0