############

This is an attempt to resolve the Items below as a code challenge


1 - Write an API endpoint that accepts a GitHub ID and returns Follower GitHub ID’s 
(up to 5 Followers total) associated with the passed in GitHub ID.  
Retrieve data up to 3 levels deep, repeating the process of retrieving Followers 
(up to 5 Followers total) for each Follower found.  Data should be returned in JSON format.


2 - Write an API endpoint that accepts a GitHub ID and retrieves the Repository names 
(up to 3 Repositories total) associated with the passed in GitHub ID


This is a spring Boot project

To try it out

you can clone the project and run it locally

the URL for the endpoint to retrieve followers of a given github ID is
http://localhost:2019/githubID/followers

Example URL : http://localhost:2019/latifbenzzine/followers
where latifbenzzine is github ID.


to retrieve the repos associated with a give github ID
the URL is http://localhost:2019/githubID/repos

Example URL : http://localhost:2019/latifbenzzine/repos

In both cases the output is JSON Format with the github ID (Also known as login)

for the followers and the name of the repo in the second case.

If you have any questions or comments about this code, plesae feel free to contact me @ latifbenzzine@gmail.com.