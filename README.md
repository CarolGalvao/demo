# demo

  Como executar a aplicação
 * Estrutura dos arquivos/pacotes
 - business: Contém as classes responsáveis pela lógica do projeto.
 - controller: Possui as classes resposáveis pela interação do usuário com a api, como permitir adicionar uma nova rota ou buscar a melhor rota entre dois pontos.
 - model: Armazenam as classes que representam entidades e ajudam a armazenar e buscar os dados.
 - response: Encapsula as classes que irão estrutura as respostas enviadas ao controller e portanto ao usuário.

 * Explique as decisões de design adotadas para a solução
-  Para resolver a lógica do projeto fiz uso da busca em profundidade com o algoritmo dikjstra.

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