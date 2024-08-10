Create Table Period(
    perno int,
    name varchar(255),
    Constraint pk_perno primary key (perno)
);

Create Table skills(
    skino int,
    Title varchar(255),
    Constraint pk_skino primary key (skino)
);


Create Table project(
    prono int,
    projectName varchar(255),
    dateproject date,
    perno int,
    description varchar(1200),
    Constraint pk_Projet primary key (prono),
    Constraint fk_period foreign key (perno)
        references period (perno) 
);

Create Table Requirement(
    skino int,
    prono int,
    Constraint pk_ProjectSkill primary key(skino,prono),
     Constraint fk_project foreign key (prono)
        references project (prono),
     Constraint fk_skillRequirement foreign key (skino)
        references skills (skino)

)