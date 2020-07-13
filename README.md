# jokenpo
BTG



## INSTRUÇÕES

### Cadastrar jogador

POST - /player
{
    "name" : "??"
}

### Atualizar jogador
PUT - /player
{
	"id" : "??"
    "name" : "??"
}

### Listar Jogadores
GET - /player

### Obter Jogador
GET - /player/{?}

### Deletar Jogadores
DELETE - /player/{?}

### Realizar jogada
POST - /move/{?}/player/{?}

### Atualizare jogada
PUT - /move/{?}/player/{?}

### ver jogadas
GET - /move

### ver jogadas de um jogador
GET - /move/player/{?}

### deletar jogada
DELETE - /move/player/{?}

### Verificar resultado
GET - /play