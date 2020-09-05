# demo

  Como executar a aplicação
 * Estrutura dos arquivos/pacotes
 * Explique as decisões de design adotadas para a solução
 * Descreva sua APÌ Rest de forma simplifica
 - Path: /
     - POST - Criar nova rota 
         - Params - {"source": "GRU", "destination": "CDG", "cost": 3}
         - Response 200 route crieted
         //To do
         - Response 400 - {Invalid cost for route}
     - GET - Find Best Route
         - Params - ?source=GRU&destination=CDG
         - Response 200 - {Route:  GRU - BRC - SCL - ORL  > $35}
         - Response 200 - {There is no path}