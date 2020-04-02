/* Next values are optional for start application */
USE `vehicles_sales`;

INSERT INTO `models` (`id`, `model_name`, `basic_price`)
VALUES (1, 'МАЗ 5440М5', 75000),
       (2, 'МАЗ 107', 85000),
       (3, 'МАЗ 251', 110000),
       (4, 'МАЗ 303', 100000),
       (5, 'МАЗ 257', 60000);



INSERT INTO `OPTIONS` (`id`, `Name`, `option_type`)
VALUES (1, 'Тип дисков', 0),
       (2, 'Тип светотехники', 0),
       (3, 'Кресла повышенной комфортности', 3),
       (4, 'Количество мест для сидения', 1),
       (5, 'ESP', 1);



INSERT INTO `OPTION_VALUES` (`id`, `OPTION_ID`, `value`, `description`, `price`)
VALUES (1, 1, 'Легкосплавные',        '4 легкосплавных диска для грузового автомобиля производсвта ф. Continental',4000),
       (2, 1, 'Штампованные',        '4 штампованных диска для грузового автомобиля производсвта БМЗ', 2000),
       (3, 1, 'Легкосплавные',        '6 легкосплавных дисков для автобуса производсвта ф. Continental',        6000),
       (4, 1, 'Штампованные',        '6 штампованных дисков для автобуса производсвта БМЗ', 3000),
       (5, 1, 'Легкосплавные',        '4 легкосплавных диска для автобуса производсвта ф. Continental', 4500),
       (6, 1, 'Штампованные',        '4 штампованных диска для автобуса производсвта БМЗ', 2200),


       (7, 2, 'Светодиодная',        'Светодиодная светотехника для грузового автомобиля производсвта ф. Hella',        3000),
       (8, 2, 'Галогеновая',        'Галогеновая светотехника для грузового автомобиля производсвта Руденского оптического завода',        1000),
       (9, 2, 'Ксеноновая',        'Ксеноновая светотехника для грузового автомобиля производсвта ф. Hella',        1800),

       (10, 2, 'Светодиодная',        'Светодиодная светотехника для автобуса производсвта ф. Hella', 2900),
       (11, 2, 'Галогеновая',        'Галогеновая светотехника для автобуса производсвта Руденского оптического завода',        1200),
       (12, 2, 'Ксеноновая',        'Ксеноновая светотехника для автобуса производсвта ф. Hella', 2100),

/*251*/
       (13, 4, '45 мест',        '45 сидячих мест при более плотной компановке, 5 мест сзади', 13500),
       (14, 4, '44 места',        '44 сидячих мест при более плотной компановке, 4 места сзади', 13200),
       (15, 4, '36 мест',        '36 сидячих мест при свободной компановке, 4 места сзади', 12600),
/*107*/
       (16, 4, '33',        '33 сидячих мест при свободной компановке. Дешевые сиденья', 4950),
       (17, 4, '35',        '35 сидячих мест при более плотной компановке. Дешевые сиденья', 5250),
       (18, 4, '39',        '39 сидячих мест при более плотной компановке, уменьшена накопительная площадка. Дешевые сиденья',        5850),
/*303*/
       (19, 4, '33',        '33 сидячих мест при свободной компановке. Премиум сиденья', 8250),
       (20, 4, '35',        '35 сидячих мест при более плотной компановке. Премиум сиденья', 8750),
       (21, 4, '39',        '39 сидячих мест при более плотной компановке, уменьшена накопительная площадка. Премиум сиденья',        9750),


       (22, 5, '1', 'С системой ESP для грузового автомобиля', 1500),
       (23, 5, '0', 'Без системы ESP для грузового автомобиля', 0),
       (24, 5, '1', 'С системой ESP для автобуса', 1500),
       (25, 5, '0', 'Без системы ESP для автобуса', 0);

INSERT INTO `available_model_OPTION_Values` (`model_ID`, `OPTION_values_ID`)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 3),
       (3, 4),
       (4, 5),
       (4, 6),
       (5, 5),
       (5, 6),

       (1, 7),
       (1, 8),
       (1, 9),
       (2, 10),
       (2, 11),
       (2, 12),
       (3, 10),
       (3, 11),
       (3, 12),
       (4, 10),
       (4, 11),
       (4, 12),
       (5, 10),
       (5, 11),
       (5, 12),

       (2, 13),
       (2, 14),
       (2, 15),
       (3, 16),
       (3, 17),
       (3, 18),
       (4, 19),
       (4, 20),
       (4, 21),
       (5, 19),
       (5, 16),

       (1, 22),
       (1, 23),
       (3, 24),
       (3, 25),
       (5, 24),
       (5, 25);


INSERT INTO `CONFIGURATIONS` (`id`, `name`, `MODEL_ID`)
VALUES (1, 'МАЗ 257 школьный автобус', 5),
       (2, 'МАЗ 5440М5. Базовая комлектация',1),
       (3, 'МАЗ 5440М5. Полная комлектация', 1),
       (4, 'МАЗ 303. Бери не пожалеешь!!!', 4);

INSERT INTO `selected_config_OPTION_Values` (`config_ID`, `OPTION_value_ID`)
VALUES (1, 6),
       (1, 11),
       (1, 16),
       (1, 24),
       (2, 2),
       (2, 8),
       (2, 23),
       (3, 1),
       (3, 7),
       (3, 22),
       (4, 5),
       (4, 10),
       (4, 21),
       (4, 25);