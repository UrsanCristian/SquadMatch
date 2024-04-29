# Location
INSERT INTO public.location(id, address, image_url, stadium)
VALUES (1,'Strada Mareșal Constantin Prezan, Cluj-Napoca','https://i.imgur.com/A4Jc6Zm.jpg','Baza Sportiva Gheorgheni'),
        (2,'Strada Parâng, Cluj-Napoca','https://i.imgur.com/zD8AMwn.jpeg','Baza Sportiva La Terenuri'),
        (3,'Strada Clăbucet, Cluj-Napoca','https://i.imgur.com/vtcskFl.jpeg','Baza Sportiva Unirea');

#User
INSERT INTO public.users(id, email, first_name, is_active, is_admin, last_name, losses, password, picture_url, username, wins)
	VALUES (2, 'paul@gmail.com', 'Paul', true, false, 'Pop', 0, 'paul', 'https://i.imgur.com/afUEk7n.png', 'Paul', 0),
			(3, 'radu@gmail.com', 'Radu', true, false, 'Ionescu', 0, 'radu', 'https://i.imgur.com/afUEk7n.png', 'Radu', 0),
			(4, 'tudor@gmail.com', 'Tudor', true, false, 'Avram', 0, 'tudor', 'https://i.imgur.com/afUEk7n.png', 'Avram', 0)

#Lobby
INSERT INTO public.lobby(
	id, is_open, max_players, scheduled_time, title, creator_id, location_id)
	VALUES (1, true, 12, '2022-12-31 23:59:59', '6v6 Match', 1, 1),
			(2, true, 12, '2022-12-31 23:59:59', 'Second Match', 2, 1),
			(3, true, 12, '2022-12-31 23:59:59', 'Third Match', 3, 1),
			(4, true, 12, '2022-12-31 23:59:59', 'Fourth Match', 4, 1);

#Teams
INSERT INTO public.lobby_team1(lobby_teams1_id, team1_id) VALUES (1, 1), (1, 2);

INSERT INTO public.lobby_team2(lobby_teams2_id, team2_id) VALUES (1, 3), (1, 4);

