create table events(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    sigla varchar(10) not null,
    descricao varchar(200) not null,

    primary key(id)
);