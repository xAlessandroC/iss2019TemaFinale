const config = {
    floor: {
        size: { x: 50, y: 50                   }
    },
    player: {
        //position: { x: 0.5, y: 0.5 },		//CENTER
        position: { x: 0.124, y: 0.14 },		//INIT
        //position: { x: 0.8, y: 0.85 },		//END
        speed: 0.2
    },
    sonars: [
	/*
        {
            name: "sonar1",
            position: { x: 0.12, y: 0.05 },
            senseAxis: { x: false, y: true }
        },
       {
            name: "sonar2",
            position: { x: 0.94, y: 0.88},
            senseAxis: { x: true, y: false }
        }
		*/
      ],
    movingObstacles: [
/*      {
            name: "movingobstacle",
            position: { x: .64, y: .42 },
            directionAxis: { x: true, y: true },
            speed: 0.2,
            range: 28
        },
 
        {
            name: "wall",
            position: { x: 0.0, y: 0.6 },
            directionAxis: { x: true, y: false },
            speed: 0.0078,
            range: 120
        }
		 */
    ],
   staticObstacles: [
   
        {
            name: "fridge",
            centerPosition: { x: 0.53, y: 1.0},
            size: { x: 0.17, y: 0.07}
        },

        {
            name: "dishwasher",
            centerPosition: { x: 0.93, y: 0.1},
            size: { x: 0.047, y: 0.17        }
		},
        {
            name: "pantry",
            centerPosition: { x: 0.20, y: 0.0},
            size: { x: 0.17, y: 0.045        }
		},
		
        /*{
            name: "table1x1",
            centerPosition: { x: 0.383, y: 0.73},
            size: { x: 0.11, y: 0.12    }
		},*/
		{
            name: "table2x1",
            centerPosition: { x: 0.37, y: 0.66},
            size: { x: 0.10, y: 0.24     }
		},

        {
        name: "wallUp",
			centerPosition: { x: 0.48, y: 0.97},
			size: { x: 0.89, y: 0.01}
        },
        {
            name: "wallDown",
            centerPosition: { x: 0.45, y: 0.37},
            size: { x: 0.85, y: 0.01}
        },
        {
            name: "wallLeft",
            centerPosition: { x: 0.02, y: 0.45},
            size: { x: 0.01, y: 0.88}
        },
        {
            name: "wallRight",
            centerPosition: { x: 0.62, y: 0.5},
            size: { x: 0.01, y: 0.99}
        },
		/*{
            name: "tuningA1",
            centerPosition: { x: 0.50, y: 0.93},
            size: { x: 0.95, y: 0.01}
        },
		{
            name: "mio1",
            centerPosition: { x: 0.65, y: 0.80},
            size: { x: 0.95, y: 0.01}
        },
		{
            name: "mio2",
            centerPosition: { x: 0.65, y: 0.67},
            size: { x: 0.95, y: 0.01}
        },
		{
            name: "mio3",
            centerPosition: { x: 0.65, y: 0.54},
            size: { x: 0.95, y: 0.01}
        },
		{
            name: "mio4",
            centerPosition: { x: 0.65, y: 0.41},
            size: { x: 0.95, y: 0.01}
        },
		{
            name: "tuningB1",
            centerPosition: { x: 0.06, y: 0.50},
            size: { x: 0.01, y: 0.95}
        },
		{
            name: "mio5",
            centerPosition: { x: 0.19, y: 0.50},
            size: { x: 0.01, y: 0.95}
        },
		{
            name: "mio6",
            centerPosition: { x: 0.32, y: 0.50},
            size: { x: 0.01, y: 0.95}
        },
		{
            name: "mio7",
            centerPosition: { x: 0.45, y: 0.50},
            size: { x: 0.01, y: 0.95}
        },
		{
            name: "mio8",
            centerPosition: { x: 0.58, y: 0.50},
            size: { x: 0.01, y: 0.95}
        }*/
		
		
    ]
}

export default config;
