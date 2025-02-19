CREATE TABLE tb_movie_streaming (
  movie_id INTEGER,
  streaming_id INTEGER,
  CONSTRAINT fk_movie_streaming_movie FOREIGN KEY(movie_id) REFERENCES tb_movie(id),
  CONSTRAINT fk_movie_streaming_streaming FOREIGN KEY(streaming_id) REFERENCES tb_streaming(id)
);