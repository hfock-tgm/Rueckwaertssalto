var scCommands = function()
{
	chain.addNext("schema", "text", "schema.txt");
	chain.addNext("graph", "png", "schema.png");
	chain.executeOn(catalog, connection);
	print('Created files "schema.txt" and "schema.png"');
};

scCommands();
