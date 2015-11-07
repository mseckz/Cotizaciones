
CREATE DATABASE cotizaciones;

use cotizaciones;


CREATE TABLE configVariable(
    idConfigVariable int primary key auto_increment,
    idPadre int,
    codigo varchar(10),
    descripcion varchar(50),
    flagEstado bit default 1
);

CREATE TABLE sucursal(
    idSucursal int primary key auto_increment,
	codigoUbigeo bigint not null,
    nombre varchar(100) not null,
    flagEstado bit default 1
);

CREATE TABLE vendedor(
    idVendedor int primary key auto_increment,
    idSupervisor int,
    codigoVendedor char(8) not null,
    docIdentidad char(8) not null,
    nombres varchar(100),
    apellidoPaterno varchar(80),
    apellidoMaterno varchar(80),
    telefono varchar(9),
    correo varchar(100),
    idSucursal int not null,
    nombreSucursal varchar(100),
    fechaNacimiento datetime,
    fechaRegistro datetime default CURRENT_TIMESTAMP,
    flagHabilitado bit default 1,
    flagEstado bit default 1,
    FOREIGN KEY (idSucursal) references Sucursal(idSucursal),
    FOREIGN KEY (idSupervisor) references vendedor(idVendedor)
);


create table usuario(
	IdUsuario int primary key auto_increment,
    idVendedor int,
    correo varchar(20),
    clave varchar(300),
    flagHabilitado bit default 1,
    flagEstado bit default 1,
    FOREIGN KEY (idVendedor) references vendedor(idVendedor)
);

CREATE TABLE cliente(
    idCliente int primary key auto_increment,
    codigoCliente char(8) not null,
    razonSocial varchar(100),
    nombres varchar(100),
    apellidos varchar(100),
    idTipoCliente int not null,
    descripTipoCliente varchar(50),
    idTipoDocumento int not null,
    descripTipoDoc varchar(50),
    numeroDocumento varchar(13),
    flagEstado bit default 1,
    FOREIGN KEY (idTipoCliente) references configVariable(idConfigVariable),
    FOREIGN KEY (idTipoDocumento) references configVariable(idConfigVariable)
); 


CREATE TABLE auto(
    idAuto int primary key  auto_increment,
    codigoAuto char(8) not null,
    idTipoAuto int not null,
    descripcionTipo varchar(50),
    idMarca int not null,
    marca  varchar(100) not null,
    idModelo int not null,
    modelo varchar(100) not null,
    anio int not null,
    precio decimal not null,
    urlFoto varchar(100),
    flagEstado bit default 1,
    FOREIGN KEY (idTipoAuto) references configVariable(idConfigVariable),
    FOREIGN KEY (idMarca) references configVariable(idConfigVariable),
    FOREIGN KEY (idModelo) references configVariable(idConfigVariable)
);

CREATE TABLE cotizacion(
    idCotizacion int primary key auto_increment,
    codigoCotizacion char(8) not null,
    idVendedor int not null,
    idCliente int not null,
    idAuto int not null,
    idTipoMoneda int not null,
    descripTipoMoneda varchar(50),
    cantidad int,
    precio decimal,
    importe decimal,
    flagEstado bit default 1,
    FOREIGN KEY (idVendedor) references vendedor(idVendedor),
    FOREIGN KEY (idCliente) references cliente(idCliente),
    FOREIGN KEY (idAuto) references auto(idAuto),
    FOREIGN KEY (idTipoMoneda) references configVariable(idConfigVariable)
);

CREATE TABLE auditoria(
	idAuditoria int primary key auto_increment,
    idUsuario int not null,
    idRegistroTabla int not null,
	nombreTabla varchar(100) not null,
    fechaRegistro datetime not null,
    operacion varchar(100) not null
);
 
/*Evaluacion Crediticia*/

