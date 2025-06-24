DROP DATABASE IF EXISTS "TiroConArco";
CREATE DATABASE "TiroConArco";
\c "TiroConArco";

CREATE TABLE "Arquero" (
    "idArquero" SERIAL PRIMARY KEY,
    "nombre" VARCHAR(50) NOT NULL,
    "apellido" VARCHAR(50) NOT NULL,
    "marcaPersonal" INTEGER,
    "categoria" VARCHAR(50) NOT NULL,
    "asociacion" VARCHAR(100)
);

CREATE TABLE "Arco" (
    "idArco" SERIAL PRIMARY KEY,
    "idArquero" INTEGER NOT NULL,
    "tipo" VARCHAR(50) NOT NULL,
    "marca" VARCHAR(50) NOT NULL,
    "libraje" INTEGER NOT NULL,
    "apertura" DECIMAL(5,2) NOT NULL,
    "peso" DECIMAL(5,2) NOT NULL,
    FOREIGN KEY ("idArquero") REFERENCES "Arquero"("idArquero") ON DELETE CASCADE
);

CREATE TABLE "Entrenamiento" (
    "idEntrenamiento" SERIAL PRIMARY KEY,
    "idArquero" INTEGER NOT NULL,
    "fecha" DATE NOT NULL,
    FOREIGN KEY ("idArquero") REFERENCES "Arquero"("idArquero") ON DELETE CASCADE
);

CREATE TABLE "Distancia" (
    "idDistancia" SERIAL PRIMARY KEY,
    "idEntrenamiento" INTEGER NOT NULL,
    "metros" INTEGER NOT NULL,
    FOREIGN KEY ("idEntrenamiento") REFERENCES "Entrenamiento"("idEntrenamiento") ON DELETE CASCADE
);

CREATE TABLE "Serie" (
    "idSerie" SERIAL PRIMARY KEY,
    "idDistancia" INTEGER NOT NULL,
    "flecha1" INTEGER NOT NULL,
    "flecha2" INTEGER NOT NULL,
    "flecha3" INTEGER NOT NULL,
    "flecha4" INTEGER NOT NULL,
    "flecha5" INTEGER NOT NULL,
    "flecha6" INTEGER NOT NULL,
    FOREIGN KEY ("idDistancia") REFERENCES "Distancia"("idDistancia") ON DELETE CASCADE
);

CREATE TABLE "Competencia" (
    "idCompetencia" SERIAL PRIMARY KEY,
    "idArquero" INTEGER NOT NULL,
    "nombre" VARCHAR(100) NOT NULL,
    "fechaInicio" DATE NOT NULL,
    "fechaTermino" DATE NOT NULL,
    "puntajeClasificatorio" INTEGER NOT NULL,
    "posicionClasificatorio" INTEGER NOT NULL,
    "posicionROI" INTEGER NOT NULL,
    "maximoROI" INTEGER NOT NULL,
    FOREIGN KEY ("idArquero") REFERENCES "Arquero"("idArquero") ON DELETE CASCADE
);
