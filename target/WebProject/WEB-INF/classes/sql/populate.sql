use time_tracking;

-- access
INSERT INTO access(id, message)
VALUES
    (DEFAULT, 'allow'),
    (DEFAULT, 'deny');

-- role
INSERT INTO role(id, name)
VALUES
    (DEFAULT, 'admin'),
    (DEFAULT, 'user'),
    (DEFAULT, 'anonymous');

-- sex
INSERT INTO sex(id, sex)
VALUES
    (DEFAULT, 'sex.none'),
    (DEFAULT, 'sex.male'),
    (DEFAULT, 'sex.female');

-- status
INSERT INTO status(id, status_name)
VALUES
    (DEFAULT, 'private'),
    (DEFAULT, 'public');


-- category
INSERT INTO category(id, category_name)
VALUES
    (DEFAULT, 'other'),
    (DEFAULT, 'entertainment'),
    (DEFAULT, 'sport');


-- city
INSERT INTO city(id, city_name)
VALUES
    (DEFAULT, 'city.none'),
    (DEFAULT, 'city.kyiv'),
    (DEFAULT, 'city.kharkov'),
    (DEFAULT, 'city.lviv'),
    (DEFAULT, 'city.poltava'),
    (DEFAULT, 'city.mykolaiv'),
    (DEFAULT, 'city.dnipro'),
    (DEFAULT, 'city.odessa'),
    (DEFAULT, 'city.sumy');

-- user
INSERT INTO user(id, role_id, username, password, sex_id, city_id, access_id)
VALUES
    (DEFAULT, (SELECT id FROM role WHERE name='admin'), 'Admin', 'e94c6d5f1517e3d345b3e4b8e8cfe15f73639b94f0a329b6364b496990c27ce9ac009ee8b99856dd4614ff136e8a6000e3ec9d3e441b97653280cff5d921fbad', DEFAULT, DEFAULT, (SELECT id FROM access WHERE message='allow')),
    (DEFAULT, (SELECT id FROM role WHERE name='user'), 'User', '56db0b6322d9dea7e9272cbddab6445ba9eac7125e655edb55a7183476e47935de0c838892d897536caf4582c388d0cfe5e9379c43ea432d020e87272a99624e', DEFAULT, DEFAULT, (SELECT id FROM access WHERE message='allow'));

-- activity
INSERT INTO activity(id, activity_name, category_id, city_id, description, status_id, count_customers, owner_id)
VALUES
    (DEFAULT, 'Fest', (SELECT id FROM category WHERE category_name='sport'),
     (SELECT id FROM city WHERE city_name='city.kyiv'), DEFAULT, (SELECT id FROM status WHERE status_name='public'), DEFAULT, (SELECT id FROM user WHERE id=1)),
    (DEFAULT, 'Tournament', (SELECT id FROM category WHERE category_name='sport'),
     (SELECT id FROM city WHERE city_name='city.kyiv'), DEFAULT, (SELECT id FROM status WHERE status_name='private'), DEFAULT, (SELECT id FROM user WHERE id=1)),
    (DEFAULT, 'Study', (SELECT id FROM category WHERE category_name='other'),
     (SELECT id FROM city WHERE city_name='city.kyiv'), DEFAULT, (SELECT id FROM status WHERE status_name='private'), DEFAULT, (SELECT id FROM user WHERE id=1));

-- user's activities
INSERT INTO users_activities(activity_id, user_id, spent_time_last_update_minute, total_spent_time_minute, access_id)
VALUES
    (1, 1, 0, 0, 1),
    (2, 1, 0, 0, 1),
    (3, 1, 0, 0, 1);
