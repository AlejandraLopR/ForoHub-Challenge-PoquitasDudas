create table usuarios(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correoElectronico varchar(100) not null,
    contrasena varchar(300) not null unique,
    tipoPerfil varchar(100) not null,

    primary key(id)

);