create table supervisor(
codigo bigserial not null,
nombre text not null,
estado boolean not null,
constraint supervisor_pk primary key (codigo)
);



create table departamento(
codigo bigserial not null,
nombre text not null,
estado boolean not null,
constraint departamento_pk primary key (codigo)
);


create table empleado(
codigo bigserial not null,
identificacion text not null,
nombre text not null,
direccion text not null,
telefono text,
sueldo numeric(9,2) not null,
estado boolean not null,
id_departamento bigint not null,
id_supervisor bigint,
constraint empleado_pk primary key (codigo),
CONSTRAINT empl_depar_fkey FOREIGN KEY (id_departamento) REFERENCES departamento(codigo),
CONSTRAINT empl_super_fkey FOREIGN KEY (id_supervisor) REFERENCES supervisor(codigo)
);
