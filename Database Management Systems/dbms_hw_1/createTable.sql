create table organizer(
	organizer_name varchar(20) not null,
    CONSTRAINT organizer_pk primary key (organizer_name) 
);
create table location(
	location_name varchar(30) not null,
    CONSTRAINT location_pk primary key(location_name) 
    
);
create table reserves(
    res_id varchar(5) not null,
	org_name varchar(20) not null,
    loc_name varchar(20) not null,
    day varchar(20) not null,
    start int not null,
    end int not null,
    type varchar(20) not null,
    primary key(res_id)
); 

create table EventType(
	locName varchar(20) not null,
    event_type varchar(20) not null
);


ALTER TABLE EventType ADD CONSTRAINT event_location FOREIGN KEY event_location(locName) REFERENCES location(location_name);
ALTER TABLE reserves ADD CONSTRAINT reserves_location FOREIGN KEY reserves_location(loc_name) REFERENCES location(location_name);
ALTER TABLE reserves ADD CONSTRAINT reserves_organizer FOREIGN KEY reserves_organizer(org_name) REFERENCES organizer(organizer_name);

    