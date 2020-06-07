insert into karton (id) values (1);
insert into karton (id) values (2);
insert into karton (id) values (3);

insert into pacijent (name, lastname, karton_id) values ('Petar', 'Petrovic', 1);
insert into pacijent (name, lastname, karton_id) values ('Srecko', 'Sreckovic', 2);
insert into pacijent (name, lastname, karton_id) values ('Marko', 'Markovic', 3);

insert into dijagnoza (name) values ('Essential tremor');
insert into dijagnoza (name) values ('Parkinson disease');
insert into dijagnoza (name) values ('Restless leg syndrome');
insert into dijagnoza (name) values ('Anxiety');
insert into dijagnoza (name) values ('Epilepsy');--5
insert into dijagnoza (name) values ('Alcohol abuse');
insert into dijagnoza (name) values ('Migraine');
insert into dijagnoza (name) values ('Developmental disability');
insert into dijagnoza (name) values ('Common cold');
insert into dijagnoza (name) values ('Chronic sinusitis');--10
insert into dijagnoza (name) values ('Bruise');
insert into dijagnoza (name) values ('Alzheimer disease');
insert into dijagnoza (name) values ('Delirium');
insert into dijagnoza (name) values ('Depression');
insert into dijagnoza (name) values ('Dementia');

insert into lek (name) values ('Beta blockers (Propranolol)');
insert into lek (name) values ('Anti-seizure medications (Primidone, Gabapentin, Topiramate, Topiramate)');
insert into lek (name) values ('Tranquilizers (Clonazepam)');
insert into lek (name) values ('Botox injections');
insert into lek (name) values ('Cadibopa-levodopa');--5
insert into lek (name) values ('Dopamine agonists');
insert into lek (name) values ('Anticholinergics');
insert into lek (name) values ('Amantadine');
insert into lek (name) values ('Opioids (Oxycontin, Roxicdone, Ultram, ConZip, others)');
insert into lek (name) values ('Muscle relaxants and sleep medications');--10
insert into lek (name) values ('Drugs affecting calcium channels (Gabapetin, Gabapentin enacarbil, Pregabalin)');
insert into lek (name) values ('Medications that increase dopamine in the brain (Ropinirole, Rotigotine, Pramipexole)');
insert into lek (name) values ('Psychotherapy');
insert into lek (name) values ('Antidepressants (Seratonin)');
insert into lek (name) values ('Detox and withrawal');--15
insert into lek (name) values ('Supportive environment and counseling');
insert into lek (name) values ('Educational assistance');
insert into lek (name) values ('Pain relievers(Ibuprofen, Caffeine Aspirin, Acetaminophen)');
insert into lek (name) values ('Triptans (Rizatriptan)');
insert into lek (name) values ('Anti-nausea drugs (Metoclopramide,Chlorpromazine, Prochlorperazine)');--20
insert into lek (name) values ('Decongestant nasal sprays');
insert into lek (name) values ('Cough syrups');
insert into lek (name) values ('Aspirin desensitization treatment');
insert into lek (name) values ('Cholinesterase inhibitors (Donepezil, Galantamine, Rivastigmine)');
insert into lek (name) values ('Memantine');--25

insert into dijagnoza_lek (dijagnoza_id,lek_id) values (1,1);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (1,2);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (1,3);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (1,4);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (2,5);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (2,6);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (2,7);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (2,8);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (3,9);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (3,10);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (3,11);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (3,12);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (4,13);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (4,14);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (6,15);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (6,13);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (6,16);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (8,16);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (8,17);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (7,18);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (7,19);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (7,9);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (9,18);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (9,21);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (9,22);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (10,21);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (10,23);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (12,24);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (12,25);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (13,16);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (14,13);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (14,14);
insert into dijagnoza_lek (dijagnoza_id,lek_id) values (15,13);

insert into simptom (name) values ('Seizure');
insert into simptom (name) values ('Abnormal involuntary movements');
insert into simptom (name) values ('Problems with movements');
insert into simptom (name) values ('Headache');
insert into simptom (name) values ('Dizziness');
insert into simptom (name) values ('Disturbance of memory');
insert into simptom (name) values ('Weakness');
insert into simptom (name) values ('Leg pain');
insert into simptom (name) values ('Sleepiness');
insert into simptom (name) values ('Apnea');
insert into simptom (name) values ('Depression');
insert into simptom (name) values ('Sharp chest pain');
insert into simptom (name) values ('Shortness of breath');
insert into simptom (name) values ('Insomnia');
insert into simptom (name) values ('Nervousness');

insert into test (name) values ('Radiographic imaging procedure');
insert into test (name) values ('Hematologic tests');
insert into test (name) values ('Complete blood count');
insert into test (name) values ('X ray');
insert into test (name) values ('Glucose measurement');
insert into test (name) values ('Intravenous fluid replacement');
insert into test (name) values ('Kidney function tests');
insert into test (name) values ('Magnetic resonance imaging');
insert into test (name) values ('Physical therapy exercises');
insert into test (name) values ('Ophthalmic examination and evaluation');
insert into test (name) values ('Depression screen');

insert into pregled(dijagnoza_id, karton_id) values (1, 1);
insert into pregled(dijagnoza_id, karton_id) values (2, 2);
insert into pregled(dijagnoza_id, karton_id) values (3, 3);
insert into pregled(dijagnoza_id, karton_id) values (4, 3);

insert into pregled_simptomi (pregled_id, simptom_id) values (1, 2);
insert into pregled_simptomi (pregled_id, simptom_id) values (1, 3);
insert into pregled_simptomi (pregled_id, simptom_id) values (1, 4);
insert into pregled_simptomi (pregled_id, simptom_id) values (1, 5);
insert into pregled_simptomi (pregled_id, simptom_id) values (1, 1);
insert into pregled_simptomi (pregled_id, simptom_id) values (2, 2);
insert into pregled_simptomi (pregled_id, simptom_id) values (2, 6);
insert into pregled_simptomi (pregled_id, simptom_id) values (2, 5);
insert into pregled_simptomi (pregled_id, simptom_id) values (2, 7);
insert into pregled_simptomi (pregled_id, simptom_id) values (2, 3);
insert into pregled_simptomi (pregled_id, simptom_id) values (3, 2);
insert into pregled_simptomi (pregled_id, simptom_id) values (3, 8);
insert into pregled_simptomi (pregled_id, simptom_id) values (3, 4);
insert into pregled_simptomi (pregled_id, simptom_id) values (3, 9);
insert into pregled_simptomi (pregled_id, simptom_id) values (3, 10);
insert into pregled_simptomi (pregled_id, simptom_id) values (4, 15);
insert into pregled_simptomi (pregled_id, simptom_id) values (4, 11);
insert into pregled_simptomi (pregled_id, simptom_id) values (4, 12);
insert into pregled_simptomi (pregled_id, simptom_id) values (4, 13);
insert into pregled_simptomi (pregled_id, simptom_id) values (4, 14);
insert into pregled_simptomi (pregled_id, simptom_id) values (4, 4);

insert into test_simptomi (testovi_id, simptomi_id) values (3, 1);
insert into test_simptomi (testovi_id, simptomi_id) values (3, 2);
insert into test_simptomi (testovi_id, simptomi_id) values (3, 3);
insert into test_simptomi (testovi_id, simptomi_id) values (3, 4);
insert into test_simptomi (testovi_id, simptomi_id) values (2, 2);
insert into test_simptomi (testovi_id, simptomi_id) values (2, 3);
insert into test_simptomi (testovi_id, simptomi_id) values (2, 5);
insert into test_simptomi (testovi_id, simptomi_id) values (2, 7);
insert into test_simptomi (testovi_id, simptomi_id) values (1, 2);
insert into test_simptomi (testovi_id, simptomi_id) values (1, 3);
insert into test_simptomi (testovi_id, simptomi_id) values (1, 5);
insert into test_simptomi (testovi_id, simptomi_id) values (1, 6);
insert into test_simptomi (testovi_id, simptomi_id) values (4, 1);
insert into test_simptomi (testovi_id, simptomi_id) values (4, 4);
insert into test_simptomi (testovi_id, simptomi_id) values (4, 6);
insert into test_simptomi (testovi_id, simptomi_id) values (4, 5);


insert into test_dijagnoza (testovi_id, dijagnoza_id) values (3, 1);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (2, 1);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (4, 1);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (2, 3);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (5, 4);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (5, 3);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (5, 2);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (4, 2);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (5, 5);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (4, 6);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (4, 5);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (4, 4);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (1, 4);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (2, 6);
insert into test_dijagnoza (testovi_id, dijagnoza_id) values (3, 3);