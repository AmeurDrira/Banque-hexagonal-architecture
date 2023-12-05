INSERT INTO public.client (client_id, client_lastname, client_firstname) VALUES (1, 'salin', 'ervan');
INSERT INTO public.client (client_id, client_lastname, client_firstname) VALUES (2, 'gaultier', 'jean-paul');
INSERT INTO public.client (client_id, client_lastname, client_firstname) VALUES (3, 'Harmon', 'terry');
INSERT INTO public.client (client_id, client_lastname, client_firstname) VALUES (4, 'Dupuis', 'Auguste');

INSERT INTO public.account (account_id, account_creation_date,  account_balance,  client_id) VALUES (1, '2023-11-27 14:48:30.000000', 0.00, 1);
INSERT INTO public.account (account_id, account_creation_date,  account_balance,  client_id) VALUES (2, '2023-11-27 14:48:30.000000',  0.00, 2);
INSERT INTO public.account (account_id, account_creation_date,  account_balance,  client_id) VALUES (3, '2023-11-27 14:48:30.000000',  0.00, 3);
INSERT INTO public.account (account_id, account_creation_date,  account_balance,  client_id) VALUES (4, '2023-11-27 14:48:30.000000',  0.00, 4);

