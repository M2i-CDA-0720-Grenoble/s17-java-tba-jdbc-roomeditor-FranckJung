DELETE FROM `direction`;
INSERT INTO `direction` (`id`, `name`, `command`)
VALUES
    (1, 'East', 'east'),
    (2, 'South', 'south'),
    (3, 'West', 'west'),
    (4, 'North', 'north'),
    (5, 'Up', 'up'),
    (6, 'Down', 'down')
;

DELETE FROM `room`;
INSERT INTO `room` (`id`, `name`, `description`)
VALUES
    (1, "bedroom", "This is your bedroom. It's decorated with cute pink striped wallpaper. Your king-sized bed lies in a corner, and your desk sits opposite to it."),
    (2, "bathroom", "This is your bathroom. It has ocean-blue tiling on the walls, and there's a tub (and not a shower)."),
    (3, "kitchen", "This is your kitchen. There are leftovers from last night's dinner on your IKEA's EKBACKEN workbench."),
    (4, "corridor", "There's a coat rack with your cagoule hanging at it. Your shoes are sitting underneath."),
    (5, "garage", "This is your garage."),
    (6, "attic", "This is the attic. It's very dark.")
;

DELETE FROM `room_transition`;
INSERT INTO `room_transition` (`from_room_id`, `to_room_id`, `direction_id`)
VALUES
    (1, 2, 1),
    (2, 1, 3),
    (1, 4, 4),
    (4, 1, 2),
    (4, 3, 3),
    (3, 4, 1),
    (4, 5, 4),
    (5, 4, 2),
    (1, 6, 5),
    (6, 1, 6)
;
