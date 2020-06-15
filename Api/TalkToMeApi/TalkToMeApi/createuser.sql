-- Creating the simpleq database + user

use talktome;
go

create login talktome with password = 'talktome123.';
go
alter login talktome enable;
go
create user talktome for login talktome;
go

exec sp_addrolemember N'db_owner', N'talktome';
go