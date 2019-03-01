call runcrud.bat
if "%ERRORLEVEL%" == "0" goto displayinbrowser
echo Couldn't execute runcrud
goto fail

:displayinbrowser
call start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo Couldn't run browser
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.