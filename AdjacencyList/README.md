ADJLIST

constructor
	set vertex no.				    c
	instanciate vertex list			c

	for each vertex (1 > n)			max(n)
		initialize edge list		c

addEdge(with weight)
	IF [test for negative weight]		c

	IF [test if vertex exists]		    c

	IF [test if self]		    	    c

	for each edge in vertex1(0 > n-1)	max(n-1)
		IF [edge exists]	    	c
			return
	
	connect v1 to v2 /w weight		c
	connect v2 to v1 /w weight		c

addEdge(without weight)
	IF [test if vertex exists]		c

	IF [test if self]			c

	for each edge in vertex1(0 > n-1)	max(n-1)
		IF [edge exists]		c
			return

	connect v1 to v2			c
	connect v2 to v1			c

setWeight
	for each edge in vertex1(0 > n-1)	max(n-1)
		IF [edge exists]		c
			return index of edge
	IF [exists]

	for each edge in vertex2(0 > n-1)	max(n-1)
		IF [edge exists]		c
			return index of edge

	set v1 to v2 weight			c
	set v2 to v1 weight			c

printGraph
	for each vertex (1 > n)						max(n)
		IF [has edges]						
			print vertex no.				c
			for each edge in vertex (1 > n-1)		max(n-1)
				print connecting vertex and weight	c
			print newline					c
		ELSE							
			print vertex no.				c


printGraph (for a single vertex)
		IF [has edges]						
			print vertex no.				c
			for each edge in vertex (1 > n-1)		max(n-1)
				print connecting vertex and weight	c
			print newline					c
		ELSE
			print doesnt have connections			c

THETA(V + E) minne kapitel 22
	
ADJMATRIX

constructor
	set vertix no.				c
	instanciate matrix			c

	for each row (1 > n)			max(n)
		for each column(1 > n)		max(n)
			initialize edge to 0	c

addEdge (with weight)
	IF [test for negative weight]		c

	IF [test if vertex exists]		c

	IF [test if self]			c

	IF [edge exists]			c
		return
	
	connect v1 to v2 /w weight		c
	connect v2 to v1 /w weight		c

addEdge (without weight)
	IF [test for negative weight]		c

	IF [test if vertex exists]		c

	IF [test if self]			c

	IF [edge exists]			c
		return
	
	connect v1 to v2 			c
	connect v2 to v1 			c	

setWeight
	IF [test if doesnt edge exists]		c
	
	set v1 to v2 weight			c
	set v2 to v1 weight			c

printGraph
	for each row (1 > n)			max(n)
		for each column(1 > n)		max(n)
			print edge weight	c
		print newline			c

THETA(V^2) minne kapitel 22

	

	





