create table rivetlogic_tout_ToutUserStatus (
	userId LONG not null primary key,
	articleId LONG,
	toutDismissed BOOLEAN,
	toutSeen BOOLEAN,
	reminderDate DATE null
);