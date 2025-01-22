class AddDetailsToProducts < ActiveRecord::Migration[8.0]
    def up
        add_column :products, :description, :text
    end

    def down
        remove_column :products, :description
    end
end