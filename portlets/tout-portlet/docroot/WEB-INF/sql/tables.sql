create table rivetlogic_tout_ToutUserStatus (
	userId LONG not null,
	toutConfigId VARCHAR(75) not null,
	articleId LONG,
	toutDismissed BOOLEAN,
	toutSeen BOOLEAN,
	reminderDate DATE null,
	primary key (userId, toutConfigId)
);