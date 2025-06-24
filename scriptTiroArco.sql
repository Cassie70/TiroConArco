delete database if exists TiroConArco;
create database TiroConArco;
use TiroConArco;

create table Arquero(
    idArquero int not null auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    marcaPersonal int,
    categoria varchar(50) not null,
    asociacion varchar(100),
    primary key (idArquero)
);

create table Arco(
    idArco int not null auto_increment,
    idArquero int not null,
    tipo varchar(50) not null,
    marca varchar(50) not null,
    libraje int not null,
    apertura decimal(3,2) not null,
    peso decimal(3,2) not null,
    primary key (idArco),
    foreign key (idArquero) references Arquero(idArquero) on delete cascade
);

create table Entrenamiento(
    idEntrenamiento int not null auto_increment,
    idArquero int not null,
    fecha date not null,
    primary key (idEntrenamiento),
    foreign key (idArquero) references Arquero(idArquero) on delete cascade
);

create table Distancia(
    idDistancia int not null auto_increment,
    idEntrenamiento int not null,
    metros int not null,
    primary key (idDistancia),
    foreign key (idEntrenamiento) references Entrenamiento(idEntrenamiento) on delete cascade
);

create table Serie(
    idSerie int not null auto_increment,
    idDistancia int not null,
    flecha1 int not null,
    flecha2 int not null,
    flecha3 int not null,
    flecha4 int not null,
    flecha5 int not null,
    flecha6 int not null,
    primary key (idSerie),
    foreign key (idDistancia) references Distancia(idDistancia) on delete cascade
);

creat table Competencia(
    idCompetencia int not null auto_increment,
    idArquero int not null,
    nombre varchar(100) not null,
    fechaInicio date not null,
    fechaTermino date not null,
    puntajeClasificatorio int not null,
    posicionClasificatorio int not null,
    posicionROI int not null,
    maximoROI int not null,
    primary key (idCompetencia),
    foreign key (idArquero) references Arquero(idArquero) on delete cascade
);


