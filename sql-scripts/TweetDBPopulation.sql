USE `tweet_app_db`;
insert into `user` values
	(0,'blueFaceBrownHat','Cad','Bane','cb@email.co','bobasux'),
    (0,'boba','Boba','Fett','bf@email.co','ibest'),
    (0,'fennec','Fennec','Shand','cb@email.co','ilikeboba')
    ;
insert into `tweet` values
	(0, 'An old clone veteran tried to outdraw me again today.. *sigh*','dumbclones',1),
    (0, 'Republic? Empire? Who cares? I make money either way','ballin',1),
    (0, 'Boba is kinda hot right?',NULL,3),
    (0, 'Just finished painting my armour again. It looks good.', null,2)
    ;
insert into `reply` values
	(0, 'Wow Boba.. nice.', 'platonicadmiration',4,3)
    ;
insert into `user_tweet_like` values
	(3,1),
    (3,4)
    ;