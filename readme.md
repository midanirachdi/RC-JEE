<strong> Projet pi dev:</strong>  refugees camp <br>
<strong> Equipe:</strong> Tech No Logic <br>
<hr><br>
<strong> Git commit & push </strong><br>
<p>git commit -m "skeleton commit"</p><br>
<p>git push -u origin --all</p><br>
<hr><br>
<strong> Configuration git </strong><br>
<p>
1) Install git bash client <br> 
2) go to C:\Users\<your_username>\workspace <br>
3) left click : git bash here <br>
4) git config --global user.email "midani.rachdi@esprit.tn" <br>
5) git config --global user.name "Midani Rachdi" <br>
6) generate ur ssh keys with 
ssh-keygen -t rsa -C "midani.rachdi@esprit.tn" -b 4096<br>
7) press enter<br>
8) enter your passphrase once<br>
9) enter your passphrase twice<br>
10) cat ~/.ssh/id_rsa.pub | clip<br>
11) go to gitlab.com > top right corner : click on your picture 
> settings >ssh keys<br>
12) go to : C:\Users\m\.ssh\id_rsa.pub > open with notepad++ 
and copy the whole string starting with ssh-rsa
and ending with your email<br>
13) add your key in gitlab.com  <br>
14) git clone git@gitlab.com:technologic-esprit/Refugees-camp-JEE.git <br>
</p><br>
<hr><br>
<strong> References git </strong><br>
<p>https://docs.gitlab.com/ce/ssh/README.html</p><br>
<p>https://help.github.com/articles/resolving-a-merge-conflict-using-the-command-line/</p><br>
<hr><br>
<strong> Configuration de datasource wildfly </strong><br>
<p>1) go to 127.0.0.1:19990  Login:  admin  password: wildflyadmin<br>
2) go to Configuration/subsystems/datasources/view<br>
3) JDBC datasource > add > mysql datasource<br>
4) Step 2/3 jdbc driver : choose detected driver > select mysql> click next <br>
5) Step 3/3 connection settings : change database name to "refugeescamp"  pour que l'url devient 
"jdbc:mysql://localhost:3306/refugeescamp"<br>
6) type your database username and password <br>
7) go to mysqlworkbench or phpmyadmin : create a database named refugeescamp <br>
8) go to wildfly and test connection </p> 


<h4>Test</h4>

