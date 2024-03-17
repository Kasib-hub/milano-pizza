INSERT INTO public.zipcode (zipcodeid,city,state) VALUES
	 (55501,'Somerset','NJ'),
	 (55502,'Atlanta','GA'),
	 (55503,'El Paso','TX'),
	 (55504,'Chicago','IL');

INSERT INTO public.customer (telephoneid,street_address,zipcode_id) VALUES
	 (915777987,'2933 Regent',55501),
	 (987654321,'456 Elm St',55501),
	 (876543210,'789 Oak St',55501),
	 (765432109,'321 Maple St',55502),
	 (654321098,'234 Pine St',55502),
	 (543210987,'567 Birch St',55502),
	 (432109876,'890 Cedar St',55503),
	 (321098765,'123 Spruce St',55503),
	 (210987654,'456 Redwood St',55503),
	 (109876543,'789 Willow St',55504);
INSERT INTO public.customer (telephoneid,street_address,zipcode_id) VALUES
	 (998877665,'321 Cypress St',55504),
	 (887766554,'654 Sycamore St',55504),
	 (527762135,'463 gqCLc',55501),
	 (35871840,'021 teGaG',55501);

INSERT INTO public.employee (first_name,last_name,status) VALUES
	 ('Kasib','Abdullah',true),
	 ('Junhan','An',true),
	 ('John','Suero',true),
	 ('Alyssa','Ramroop',false);