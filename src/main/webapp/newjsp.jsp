<%-- 
    Document   : newjsp
    Created on : 07-Apr-2023, 3:33:20 PM
    Author     : HYPER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui" >
    <h:head>
        <title>Input Value Counter Sample</title>
    </h:head>
    <h:body>
        <h:form>
            <p:outputLabel value="Basic"/><br/>
            <p:inputText value="#{indexcontroller.inputValue}"/><br/>
            <p:commandButton value="Submit" action="#{indexcontroller.submitButtonAction()}"/><br/>
        </h:form>
    </h:body>
</html>
