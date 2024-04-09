# Location
INSERT INTO public.location(id, address, image_url, stadium)
VALUES (1,
        'Strada Mareșal Constantin Prezan, Cluj-Napoca',
        'https://www.google.com/url?sa=i&url=https%3A%2F%2Fvisitcluj.ro%2Ftourist_spot%2Fbaza-sportiva-gheorgheni-2%2F&psig=AOvVaw3KAfwa4dhOP45zgFbGBcjS&ust=1712762495018000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJDmvdK3tYUDFQAAAAAdAAAAABAK',
        'Baza Sportiva Gheorgheni'
       );

#User
INSERT INTO public.users(id, email, first_name, is_active, is_admin, last_name, losses, password, picture_url, username, wins)
	VALUES (2, 'paul@gmail.com', 'Paul', true, false, 'Pop', 0, 'paul', 'https://img.freepik.com/free-vector/isolated-young-handsome-man-different-poses-white-background-illustration_632498-859.jpg?t=st=1712676712~exp=1712680312~hmac=51fbdfaa0fb06aeabb092b76d3397a2a68a7feab4692fe7df72a4af630d78d92&w=826', 'Paul', 0),
			(3, 'radu@gmail.com', 'Radu', true, false, 'Ionescu', 0, 'radu', 'https://img.freepik.com/free-vector/isolated-young-handsome-man-different-poses-white-background-illustration_632498-859.jpg?t=st=1712676712~exp=1712680312~hmac=51fbdfaa0fb06aeabb092b76d3397a2a68a7feab4692fe7df72a4af630d78d92&w=826', 'Radu', 0),
			(4, 'tudor@gmail.com', 'Tudor', true, false, 'Avram', 0, 'tudor', 'https://img.freepik.com/free-vector/isolated-young-handsome-man-different-poses-white-background-illustration_632498-859.jpg?t=st=1712676712~exp=1712680312~hmac=51fbdfaa0fb06aeabb092b76d3397a2a68a7feab4692fe7df72a4af630d78d92&w=826', 'Avram', 0)

#Lobby
INSERT INTO public.lobby(
	id, is_open, max_players, scheduled_time, title, creator_id, location_id)
	VALUES (1, true, 12, '2022-12-31 23:59:59', '6v6 Match', 1, 1),
			(2, true, 12, '2022-12-31 23:59:59', 'Second Match', 2, 1),
			(3, true, 12, '2022-12-31 23:59:59', 'Third Match', 3, 1),
			(4, true, 12, '2022-12-31 23:59:59', 'Fourth Match', 4, 1);

