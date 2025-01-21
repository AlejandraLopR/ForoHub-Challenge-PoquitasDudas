create table cursos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    categoria varchar(100) not null,

    primary key(id)
);

create table topicos(

    id bigint not null auto_increment,
    titulo varchar(400) not null unique,
    mensaje text not null,
    fechaCreacion datetime not null,
    status boolean DEFAULT TRUE,
    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    constraint fk_topicos_autor_increment foreign key (autor_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key (curso_id) references cursos(id)

);



