CONNECT TO VCS ;
QUIESCE DATABASE IMMEDIATE FORCE CONNECTIONS;
CONNECT RESET;
 
BACKUP DATABASE VCS TO "C:\db2_backup" WITH 2 BUFFERS BUFFER 1024 PARALLELISM 1 WITHOUT PROMPTING;
 
CONNECT TO VCS;
UNQUIESCE DATABASE;
CONNECT RESET;
 
